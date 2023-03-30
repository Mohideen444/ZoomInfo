package com.zoominfo.pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoominfo.library.WebDriverCommons;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.WebDriverSetup;

public class ChatbotProp {
	
	//The insent.ai page is divided into 3 page fragments based on the functionality
	//ChatbotProp - All the chatbot functionalities except the conversation will be maintained here

	By restart = By.xpath("//span[contains(text(),\"Restart\")]//parent::button");
	By close_btn = By.xpath("//div[@data-testid=\"insent-test-card-close\"]");
	
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
	
	public void close_chatbox() {
		commons.findAndClickTheElement(close_btn);
		log.info("Close button clicked");
	}
}
