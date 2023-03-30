package com.zoominfo.pages;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoominfo.getters.UrlInfo;
import com.zoominfo.library.FileHandler.FileHandlerException.FileIsNotPresent;
import com.zoominfo.library.WebDriverCommons;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.WebDriverSetup;

public class InsentAI {
	
	//The insent.ai page is divided into 3 page fragments based on the functionality
	//InsentAI - All the page level functionalities except the chatbot and conversations will be maintained here
	
	//Locators
	By frame = By.xpath("//iframe[@id='insent-iframe']");
	By chatbot_btn = By.xpath("//div[@id='insent-launcher-icon']");
	By accept_cookies_btn = By.xpath("//a[@role='button']");
	By welcome_text = By.xpath("//div[@id=\"insent-greeting-message\"]");
	
	//Getters
	UrlInfo info;
	
	//Wait
	int wait = GlobalConfiguration.getInstance().getExplicit_wait_time();
	
	//Log
	Logger log = LoggerFactory.getLogger(getClass());
	
	//Library object
	WebDriverCommons commons = WebDriverSetup.getInstance().getWebDriverCommons();
	

	
	
	//Page Functions
	public void goToPage() throws FileIsNotPresent, IOException, ParseException {
		info = new UrlInfo();
		String url = info.getZinfoUrls("main");
		commons.goTo(url);
		log.info("Navigated to the URL : "+url);
	}
	
	public void waitForchatbot() {
		commons.waitForPresenceOfElement(frame, wait);
		log.info("Chatbot displayed");
	}
	
	public void switchToChatFrame()
	{
		WebElement ele = commons.findElement(frame);
		commons.switchToFrame(ele);
		log.info("Switched to Chat frame");
	}
	
	public void clickChat() {
		commons.waitForPresenceOfElement(chatbot_btn, wait);
		commons.findAndClickTheElement(chatbot_btn);
		log.info("Clicked the chatbot");
	}
	
	public void acceptCookies() {
		commons.waitForPresenceOfElement(accept_cookies_btn, wait);
		WebElement btn = commons.findElement(accept_cookies_btn);
		commons.javascriptExecutor("arguments[0].click();", btn);
		log.info("Inset AI page Cookies accepted");
	}
	
	public String getWelcomeText() {
		String text = commons.findElement(welcome_text).getText();
		log.info("Welcome text on the chatbot : " + text);
		return text;
	}
	
	public void goToNewPage(String url) throws InterruptedException {
		commons.openURLInNewTab(url,wait);
		log.info("New tab opened for the URL : " + url);
		log.info("waiting for 10 seconds....");
		TimeUnit.SECONDS.sleep(10);
	}
	
	public String switchToBasetab() {
		String current_url = commons.switchToMainWindow();
		return current_url;
	}
	
}
