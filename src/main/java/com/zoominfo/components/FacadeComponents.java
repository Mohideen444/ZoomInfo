package com.zoominfo.components;

import org.testng.Assert;

import com.zoominfo.pages.Conversations;
import com.zoominfo.pages.InsentAI;

public class FacadeComponents {

// Facade is a design pattern for combining multiple functions into a single unit to use it on different places.
// So In this class I combined steps as a reusable single function to reduce the scenario length
	
//Pages
	
	InsentAI insentAIPage;
	Conversations conversations;
	
	
	public void openChatbot(String message) {
		insentAIPage = new InsentAI();
		conversations = new Conversations();
		
		insentAIPage.waitForchatbot();
		insentAIPage.switchToChatFrame();
		String text = insentAIPage.getWelcomeText();
		Assert.assertEquals(text, message, "Welcome Message displayed");
		insentAIPage.clickChat();
		conversations.checkConversationIsStarted();
	}
	
	
	
	
	
}
