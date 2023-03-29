Feature: zoom info chatbot testing

  Background:
  Given user navigates to the assignment webpage
  And user clicks on accept cookies button
  
  @chat
  Scenario Outline: Perform basic validations and Restart conversation in the chatbot
  
  Given user waits for the chatbot to appear
  And user verifies the welcome message "Welcome to ZoomInfo Chat!"
  When user clicks the chatbot
  Then the chatbox opens
  When the chatbot ask for email id
 	And user enters "<invalid_email>" data
 	Then user verifies the error message "Please enter a valid email address."
 	When user enters "<valid_email>" data
 	Then the verified tick symbol is displayed
 	When user restarts the conversation
 	Then the email id field displayed again
	
  Examples:
      | invalid_email	| valid_email			|
      |	123456				| test@test1.com	|

	