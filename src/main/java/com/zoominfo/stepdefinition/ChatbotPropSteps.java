package com.zoominfo.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.zoominfo.pages.ChatbotProp;

import io.cucumber.java.en.When;

public class ChatbotPropSteps {

	//Page Object
	ChatbotProp chatbotProp = new ChatbotProp();
	
	//Log
	Logger log = LoggerFactory.getLogger(getClass());

	
	
	
	//Step Definitions
	
	@When("user restarts the conversation")
	public void user_restarts_the_conversation() {
		try {
			chatbotProp.restartConversation();
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}
	
	@When("user closes the chatbot")
	public void the_user_closes_the_chatbot() {
		try {
			chatbotProp.close_chatbox();
	} catch (Throwable e) {
		e.printStackTrace();
		Assert.fail();
	}
	}
	
}
