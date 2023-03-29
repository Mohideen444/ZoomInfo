package com.zoominfo.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoominfo.library.WebDriverCommons;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.WebDriverSetup;

public class ChatbotProp {

	By restart = By.xpath("//span[contains(text(),\"Restart\")]//parent::button");
	
	//Wait
		int wait = GlobalConfiguration.getInstance().getExplicit_wait_time();
	
	//Log
		Logger log = LoggerFactory.getLogger(getClass());
	
	//Library object
		WebDriverCommons commons = WebDriverSetup.getInstance().getWebDriverCommons();
	
		
		
	//Functions
	public void restartConversation() {
		commons.findAndClickTheElement(restart);
		log.info("Restart conversation button clicked");
	}
}
