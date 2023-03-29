package com.zoominfo.library;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class WebDriverCommons {

    WebDriver driver;
    private static int implicit_wait;
    Logger log = LoggerFactory.getLogger(WebDriverCommons.class);

    public WebDriverCommons(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverCommons(WebDriver driver, int implicit_wait) {
        this.driver = driver;
        this.implicit_wait = implicit_wait;
    }

    public void setImplicitWait(int wait) {
        this.implicit_wait = wait;
    }

    public void sendKeysWithDelay(String value, WebElement element) throws InterruptedException {
        String[] list = value.split("");
        clickTheElement(element);
        for (String letter : list) {
            element.sendKeys(letter);
            TimeUnit.MILLISECONDS.sleep(200);
        }
        element.sendKeys(Keys.ENTER);
    }

    public void sendKeysInGeneral(String value) throws InterruptedException {
        Actions builder = new Actions(driver);
        String[] list = value.split("");
        TimeUnit.SECONDS.sleep(1);
        for (String letter : list) {
            log.info("Entered Keys In General" + letter);
            builder.sendKeys(letter).perform();
            TimeUnit.MILLISECONDS.sleep(200);
        }
        builder.sendKeys(Keys.ENTER).perform();

    }
    
    public void switchToFrame(WebElement frame)
    {
    	driver.switchTo().frame(frame);
    }

    public void sendKeysAndSelectInDropdown(WebElement target, String keys) throws InterruptedException, NoSuchElementException {
        target.clear();
        sendKeysWithDelay(keys, target);
    }

    public String clearAndSendKeys(WebElement element, String text) {
        String sent_key;
        element.clear();
        sent_key = sendKeys(element, text);
        return sent_key;
    }

    public String sendKeys(WebElement element, String text) {
        throwExceptionIfElementIsNull(element);
        throwExceptionIfElementIsNotVisible(element);
        element.sendKeys(text);
        return element.getAttribute("value");
    }

    public String findElementAndSendKeys(By locator, String text) {
        WebElement element;
        element = findElement(locator);
        return sendKeys(element, text);
    }

    public void setPageLoadTimeout(int wait) {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(wait));
        log.info("Page Load Time Out set " +
                driver.manage().timeouts().getPageLoadTimeout());
    }

    public void reloadPage() {
        driver.navigate().refresh();
        log.info("Page Reloaded");
    }

    public void goTo(String url) {
        driver.get(url);
        log.info("Current URL : " + driver.getCurrentUrl());
    }

    public void waitForLoading(By spinner, int timeout) throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOfElementLocated(spinner));
        System.out.println("Page Loading : \n Loading element: " + spinner.toString());
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(spinner)));
    }

    public WebElement waitForPresenceOfElement(By spinner, int wait) {
        WebElement found_element;
        WebDriverWait wait_visiblity = new WebDriverWait(driver, Duration.ofSeconds(wait));
        log.info("Waiting for visiblity of element");
        found_element = wait_visiblity.until(ExpectedConditions.visibilityOfElementLocated(spinner));
        log.info("Element visible of element" + spinner);
        return found_element;
    }
    
    public void turnOnImplicitWait(int wait) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(wait));
        log.info("Implicit Wait Turned On --- Wait : " +
                driver.manage().timeouts().getImplicitWaitTimeout());
    }

    public List<String> getTextFromWebElements(List<WebElement> elements) {
        List<String> list = new ArrayList<String>();
        log.info("Web Element List Size : " + elements.size());
        if (elements.size() > 0) {
            for (WebElement element : elements) {
                list.add(element.getText());
                log.info("element added to the list = " + element.getText());
            }
            return list;
        } else
            return null;
    }

    public void clickTheElement(WebElement element) {
        Actions builder = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        throwExceptionIfElementIsNull(element);
        log.info("Wait for element to Be Clickable");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("Move to the Element Before Click");
        builder.moveToElement(element).perform();
        // mouseElementToViewPort(element);
        moveTheElementToCenter(element);
        log.info("Element is clickable");
        element.click();
    }


    public void mouseClickTheElement(WebElement element) {
        Actions builder = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(implicit_wait));
        throwExceptionIfElementIsNull(element);
        log.info("Wait for element to Be Clickable");
        wait.until(ExpectedConditions.elementToBeClickable(element));
        log.info("Move to the Element Before Click");

        builder.moveToElement(element).click(element);
        moveTheElementToCenter(element);
        log.info("Element is clickable");
        builder.build().perform();
    }

    public void moveElementToViewPort(WebElement element) {
        throwExceptionIfElementIsNotVisible(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        //  executor.executeScript("arguments[0].scrollIntoView(true)", element);
        executor.executeScript("window.scrollBy(2000,0)");
        log.info("Element Moved To Center");
    }

    public void moveTheElementToCenter(WebElement element) {
        throwExceptionIfElementIsNotVisible(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String scrollElementIntoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        executor.executeScript(scrollElementIntoMiddle, element);

        log.info("Element Moved To Center");
    }
    

    public void clickTheElementIfAvailable(By locator) {
        Actions builder = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            WebElement element = driver.findElement(locator);
            log.info(" Wait for element to Be Clickable");
            wait.until(ExpectedConditions.elementToBeClickable(element));
            log.info(" Move to the Element Before Click");
            builder.moveToElement(element);
            log.info("Element is clickable");
            element.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("Element is not available hence skipping", e);
        }
    }

    public WebElement findElement(By locator) throws UnableToFindElementException {
        int count = 1;
        //Attempt to Find Element Again if StaleElementExecption is observer
        while (count <= 2) {
            try {
                WebElement element;
                element = driver.findElement(locator);
                return element;
            } catch (StaleElementReferenceException e) {
                log.error("Stale Element Observed", e);
                count++;
                if (count == 3) {
                    throw e;
                }
            }
        }
        throw new UnableToFindElementException("Find Element Failed \n" + locator.toString());
    }

    public void findAndClickTheElement(By locator) throws UnableToFindElementException {
        WebElement element;
        element = findElement(locator);
        try {
            clickTheElement(element);
        } catch (StaleElementReferenceException e) {
            log.info("Handled Exception", e);
            element = findElement(locator);
            clickTheElement(element);
        }
    }


    public void hoverOnAnElement(By locator) throws UnableToFindElementException {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {

            WebElement menu = driver.findElement(locator);
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            log.info("waiting for hover over an element");
            actions.moveToElement(menu).perform();
            log.info("Mouse hovered on the respective element");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            log.error("Element is not available hence skipping", e);
        }

    }

    public void waitForUrlToBe(String url, int wait_time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(wait_time));
        log.info("wait_time: " + wait_time);
        log.info("url To Be " + url);
        log.info("Url " + driver.getCurrentUrl());
        wait.until(ExpectedConditions.urlToBe(url));
    }


    public WebElement findElementInElement(WebElement element, By locator) {
        throwExceptionIfElementIsNull(element);
        WebElement element_in_element;
        element_in_element = element.findElement(locator);
        return element_in_element;
    }

    public List<WebElement> findElementsInElement(WebElement element, By locator) {
        throwExceptionIfElementIsNull(element);
        List<WebElement> element_in_element;
        element_in_element = element.findElements(locator);
        return element_in_element;
    }

    public boolean isInnerTextEquals(String text, WebElement element) {
        log.info("Inner Text:" + element.getText() + "\nExpected Text:" + text);
        return text.equals(element.getText());
    }


    public boolean isElementNull(WebElement element) {
        return element == null;
    }
    
    public void javascriptExecutor(String js_script, WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript(js_script, element);

    }

    // Exception Methods


    public void throwExceptionIfElementIsNotVisible(WebElement element) {
        if (ExpectedConditions.visibilityOf(element) == null)
            throw new ElementNotVisibleException("Element is not Visible" + element);
        else
            log.debug("element is Visible" + element);
    }

    public void throwExceptionIfElementLocatedIsNotLocated(By locator) {
        if (ExpectedConditions.visibilityOfElementLocated(locator) == null)
            throw new ElementNotVisibleException("Element is not Visible" + locator);
        else
            log.debug("element is Visible" + locator);
    }

    public void throwExceptionIfElementIsNull(WebElement element) {
        if (isElementNull(element))
            throw new NullPointerException("Web Element is null");
    }
    
    public class ElementNotVisibleException extends RuntimeException {
        public ElementNotVisibleException(String ex_message) {
            super(ex_message);
        }
    }

    public class WebDriverCommonsException extends RuntimeException {
        public WebDriverCommonsException(String ex_message) {
            super(ex_message);
        }
    }

    public class UnableToFindElementException extends WebDriverCommonsException {

        public UnableToFindElementException(String ex_message) {
            super(ex_message);
            // TODO Auto-generated constructor stub
        }

    }


}

