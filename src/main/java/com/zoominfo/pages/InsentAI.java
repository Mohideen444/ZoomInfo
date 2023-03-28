package com.zoominfo.pages;


import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.zoominfo.getters.UrlInfo;
import com.zoominfo.library.FileHandler.FileHandlerException.FileIsNotPresent;
import com.zoominfo.library.WebDriverCommons;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.WebDriverSetup;

public class InsentAI {
	
	WebDriverCommons commons = WebDriverSetup.getInstance().getWebDriverCommons();

	//Locators
	By frame = By.xpath("//iframe[@id='insent-iframe']");
	By chatbot_btn = By.xpath("//div[@id='insent-launcher-button']");
	
	//Wait
	int wait = GlobalConfiguration.getInstance().getExplicit_wait_time();
	
	//Getters
	UrlInfo info;
	
	
	//Functions
	public void goToPage() throws FileIsNotPresent, IOException, ParseException {
		info = new UrlInfo();
		String url = info.getZinfoUrls("main");
		commons.goTo(url);
	}
	
	public void waitForchatbox() {
		commons.waitForPresenceOfElement(frame, wait);
	}
	
	public void switchToChatFrame()
	{
		WebElement ele = commons.findElement(frame);
		commons.switchToFrame(ele);
	}
	
	
	public void clickChat() {
		WebElement ele = commons.findElement(chatbot_btn);
		commons.clickTheElement(ele);
	}
}
