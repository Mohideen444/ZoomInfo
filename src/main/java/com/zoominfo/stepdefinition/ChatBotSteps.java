package com.zoominfo.stepdefinition;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zoominfo.pages.InsentAI;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class ChatBotSteps {


InsentAI insentAIPage;
Logger log = LoggerFactory.getLogger(getClass());

@Given("user navigates to the assignment webpage")
public void user_navigates_to_the_assignment_webpage() {
    try {
    	insentAIPage = new InsentAI();
    	insentAIPage.goToPage();
    	insentAIPage.waitForchatbox();
    } catch (Throwable e) {
    	e.printStackTrace();
    }
}

@When("user clicks the chatbot")
public void user_clicks_the_chatbot() {
	try {
		insentAIPage.clickChat();
	}catch (Exception e) {
		// TODO: handle exception
	}
}


}
