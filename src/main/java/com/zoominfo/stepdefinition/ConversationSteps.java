package com.zoominfo.stepdefinition;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.zoominfo.pages.ChatbotProp;
import com.zoominfo.pages.Conversations;
import com.zoominfo.pages.InsentAI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConversationSteps {


//Page Object
Conversations conversations = new Conversations();

//Log
Logger log = LoggerFactory.getLogger(getClass());




//Step Definitions

@Then("the chatbox opens")
public void the_chatbox_opens() {
	try {
		conversations.checkConversationIsStarted();
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@When("the chatbot ask for email id")
public void chatbot_ask_for_email_id() {
	try {
		conversations.waitForEmailPrompt();
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@And("user enters {string} data")
public void user_enters_invalid_data(String data) throws InterruptedException {
	try {
		conversations.clearTextInEmailField();
		conversations.enterEmailId(data);
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@Then("user verifies the error message {string}")
public void user(String message) {
	try {
		String text = conversations.getInvalidEmailErrorMessage();
		Assert.assertEquals(text, message, "Email error message displayed");
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@Then("the verified tick symbol is displayed")
public void the_verified_tick_symbol_is_displayed() {
	try {
		conversations.verifyTickEmail();
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@Then("the email id field displayed again")
public void the_email_id_field_displayed_again() {
	try {
		conversations.waitForEmailPrompt();
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}


//@When("")
//public void user() {
//}

}
