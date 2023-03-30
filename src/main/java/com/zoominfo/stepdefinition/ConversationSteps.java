package com.zoominfo.stepdefinition;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.zoominfo.pages.Conversations;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ConversationSteps {


//Page Object
Conversations conversations = new Conversations();

//Log
Logger log = LoggerFactory.getLogger(getClass());

//Variable Container
VariableContainer variableContainer;

public ConversationSteps(VariableContainer variableContainer) {
	this.variableContainer = variableContainer;
}


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

@And("the email field should be appeared")
public void chatbot_ask_for_email_id() {
	try {
		conversations.waitForEmailPrompt();
    } catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@And("user enters {string} data")
public void user_enters_email(String data) throws InterruptedException {
	try {
		conversations.clearTextInEmailField();
		conversations.enterEmailId(data);
		
		variableContainer.setName(data);
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


@And("the first name field should be appeared")
public void the_first_name_field_should_be_appeared() {
	try {
	conversations.waitForFirstName();
	} catch (Throwable e) {
    	e.printStackTrace();
    	Assert.fail();
    }
}

@When("the user enters firstname {string}")
public void the_user_enters_firstname(String name) {
	try {
	conversations.enterFirstName(name);
	conversations.verifyFirstName();
} catch (Throwable e) {
	e.printStackTrace();
	Assert.fail();
}
}

@Then("the chat prompts to select the country")
public void chat_prompts_to_select_the_country() {
	try {
		conversations.waitForCountryPrompt();
} catch (Throwable e) {
	e.printStackTrace();
	Assert.fail();
}
}

@When("the user selects the country {string}")
public void the_user_selects_the_country(String country) {
	try {
		conversations.selectCountry(country);
} catch (Throwable e) {
	e.printStackTrace();
	Assert.fail();
}
}

@Then("the thank you message {string} is displayed")
public void the_thank_you_message_is_displayed(String exp_message) {
	try {
		String act_message = conversations.getConfirmationMessage();
		
		Assert.assertEquals(act_message,exp_message);
		
} catch (Throwable e) {
	e.printStackTrace();
	Assert.fail();
}
}


@When("user checks the previous conversation is exist")
public void user_checks_the_previous_conversation_is_exist() {
	try {
		String message = variableContainer.getEmail();
		boolean status = conversations.checkPreviousMessageExist(message);
		Assert.assertEquals(status, true,"Previous records exists!");
} catch (Throwable e) {
	e.printStackTrace();
	Assert.fail();
}
}

}
