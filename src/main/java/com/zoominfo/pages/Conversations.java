package com.zoominfo.pages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoominfo.library.WebDriverCommons;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.WebDriverSetup;

public class Conversations {

	//The insent.ai page is divided into 3 page fragments based on the functionality
	//Conversation - All the chatbot conversation functionalities will be maintained here
	
	//Locators
		By conversation = By.xpath("//div[@id=\"insent-conversation-list\"]");
		By email_input = By.xpath("//input[@name=\"email\"]");
		By send_btn = By.xpath("//input[@name=\"email\"]//following-sibling::button");
		By error = By.xpath("//div[@class='sc-kEYyzF faNzMh']");
		By verified_tick = By.xpath("//div[text()='Email']//following-sibling::div//*[local-name()='svg']/*[local-name()='path']");
		By first_name = By.xpath("//div[text()=\"First Name\"]//following-sibling::div//input");
		By fn_send_btn = By.xpath("//input[@name=\"plain\"]//following-sibling::button");
		By fn_verified_tick= By.xpath("//div[text()='First Name']//following-sibling::div//*[local-name()='svg']/*[local-name()='path']");
		By country_prompt = By.xpath("//div[contains(text(),\"country\")]");
		By country_list = By.xpath("//div[@id=\"insent-buttons-message-button\"]");
		By confirm_message = By.xpath("//div[contains(text(),\"Thank you\")]");
		By chat_bot_messages=By.xpath("//div[@id=\"insent-text-message-message-wrapper-client-message\"]");
		
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
		log.info("Email id entered : "+text);
		TimeUnit.SECONDS.sleep(2);
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
	
	public void waitForFirstName() {
		commons.waitForPresenceOfElement(first_name, wait);
		log.info("First Name input field displayed");
	}
	
	public void enterFirstName(String text) throws InterruptedException {
		WebElement inp = commons.findElement(first_name);
		commons.sendKeys(inp, text);
		commons.findAndClickTheElement(fn_send_btn);
		log.info("First Name Entered : "+text);
	}
	
	public void verifyFirstName() {
		commons.waitForPresenceOfElement(fn_verified_tick, wait);
		log.info("First Name Verified!");
	}
	
	public void waitForCountryPrompt() {
		commons.waitForPresenceOfElement(country_prompt, wait);
		log.info("Please select the country");
	}
	
	public void selectCountry(String country) {
		commons.waitForPresenceOfElement(country_list, wait);
		List<WebElement> countries = commons.findElements(country_list);
		WebElement ele = commons.getElementContainsInnerText(country,countries);
		ele.click();
		log.info("Country Selected : "+country);
	}
	
	public String getConfirmationMessage() {
		commons.waitForPresenceOfElement(confirm_message, wait);
		String message = commons.findElement(confirm_message).getText();
		log.info(message);
		return message;
	}
	
	public boolean checkPreviousMessageExist(String message) {
		
		boolean stat = false;
		List<WebElement> messages = commons.findElements(chat_bot_messages);
		WebElement ele = commons.getElementContainsInnerText(message, messages);
		if(!ele.isDisplayed())
		{
			stat= true;
		}
		log.info("Previous conversations are available");
		return stat;
	}
	
	
}
