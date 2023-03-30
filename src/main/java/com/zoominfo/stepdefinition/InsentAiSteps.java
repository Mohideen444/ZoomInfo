package com.zoominfo.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.zoominfo.pages.InsentAI;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class InsentAiSteps {
	
	//Page Object
	InsentAI insentAIPage = new InsentAI();
	
	//Log
	Logger log = LoggerFactory.getLogger(getClass());
	
	
	
	
	//Step Definitions
	
	@Given("user navigates to the assignment webpage")
	public void user_navigates_to_the_assignment_webpage() {
	    try {
	    	insentAIPage.goToPage();
	    	
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}

	@Then("user clicks on accept cookies button")
	public void user_clicks_on_accept_cookies_button() {
		try {
			insentAIPage.acceptCookies();
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}

	@And("user waits for the chatbot to appear")
	public void user_waits_for_the_chatbot_to_appear() {
		try {
			insentAIPage.waitForchatbot();
			log.info("Chatbox Appeared");
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}

	@And("user verifies the welcome message {string}")
	public void user_verifies_the_welcome_message(String message) {
		try {
			insentAIPage.switchToChatFrame();
		String text = insentAIPage.getWelcomeText();
		Assert.assertEquals(text, message, "Welcome Message displayed");
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}

	@When("user clicks the chatbot")
	public void user_clicks_the_chatbot() {
		try {
			insentAIPage.clickChat();
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}
	
	@When("user goes to new {string} tab")
	public void user_goes_to_new_tab(String url) {
		try {
			insentAIPage.goToNewPage(url);
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}
	
	
	@And("the user returns to chatbot")
	public void the_user_returns_to_chatbot() {
		try {
			insentAIPage.switchToBasetab();
			insentAIPage.switchToChatFrame();
	    } catch (Throwable e) {
	    	e.printStackTrace();
	    	Assert.fail();
	    }
	}
}
