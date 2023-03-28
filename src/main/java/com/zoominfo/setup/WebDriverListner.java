package com.zoominfo.setup;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.*;



public class WebDriverListner implements WebDriverListener{
	
	Logger logger = LoggerFactory.getLogger(WebDriverListner.class);
	
	@Override
	public void beforeFindElement(WebDriver driver, By locator) {
		logger.debug("Before Find Element : \n"+locator.toString());
	}
	
	@Override
	public void afterFindElement(WebDriver driver, By locator, WebElement result) 
	{	
		logger.debug("After Find Element : \n"+locator.toString());
	}
	
	@Override
	public void afterFindElements(WebElement element, By locator, List<WebElement> result)
	{
		logger.debug("After Find Elements : \n"+element);
	}
	
	@Override
	public void beforeClick(WebElement element) 
	{	
		logger.debug("Before Click Element : \n"+element);
	}
	
	@Override
	public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
		
		logger.debug("After Send Keys: "+element.getAttribute("value"));
	}

}
