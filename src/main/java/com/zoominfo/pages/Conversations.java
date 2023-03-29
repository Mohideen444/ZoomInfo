package com.zoominfo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoominfo.library.WebDriverCommons;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.WebDriverSetup;

public class Conversations {

	//Locators
		By conversation = By.xpath("//div[@id=\"insent-conversation-list\"]");
		By email_input = By.xpath("//input[@name=\"email\"]");
		By send_btn = By.xpath("//input[@name=\"email\"]//following-sibling::button");
		By error = By.xpath("//div[@class='sc-kEYyzF faNzMh']");
		By verified_tick = By.xpath("//div[text()='Email']//following-sibling::div//*[local-name()='svg']/*[local-name()='path']");
	
	//Wait
		int wait = GlobalConfiguration.getInstance().getExplicit_wait_time();
		
	//Log
		Logger log = LoggerFactory.getLogger(getClass());
		
	//Library object
		WebDriverCommons commons = WebDriverSetup.getInstance().getWebDriverCommons();
		
		
		
		
	//Functions
	public void checkConversationIsStarted() {
		commons.waitForPresenceOfElement(conversation, wait);
		log.info("Chat box is Opened!");
	}
	
	public void waitForEmailPrompt() {
		commons.waitForPresenceOfElement(email_input, wait);
		log.info("Email id input field displayed");
	}
	
	public void clearTextInEmailField() {
		WebElement inp = commons.findElement(email_input);
		String s = Keys.chord(Keys.CONTROL, "a");
	    inp.sendKeys(s);
		inp.sendKeys(Keys.DELETE);
		log.info("Text cleared");
	}
	
	public void enterEmailId(String text) throws InterruptedException {
		WebElement inp = commons.findElement(email_input);
		commons.sendKeys(inp, text);
		commons.findAndClickTheElement(send_btn);
		log.info("Enter the email id : "+text);
	}
	
	public String getInvalidEmailErrorMessage() {
		String text = commons.findElement(error).getText();
		log.info("Invalid email error message : "+text);
		return text;
	}
	
	public void verifyTickEmail() {
		commons.waitForPresenceOfElement(verified_tick, wait);
		log.info("Email Verified!");
	}
}
