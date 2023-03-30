package com.zoominfo.stepdefinition;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.zoominfo.components.FacadeComponents;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps {
	
	//Combined page functions
FacadeComponents components = new FacadeComponents();

//Log
Logger log = LoggerFactory.getLogger(getClass());

//Step Definitions

@Given("user opens the chatbot")
public void user_opens_the_chatbox() {
	try {
		String message = "Welcome to ZoomInfo Chat!";
		components.openChatbot(message);
  } catch (Throwable e) {
  	e.printStackTrace();
  	Assert.fail();
  }
}


}
