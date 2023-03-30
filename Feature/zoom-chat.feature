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
  And the email field should be appeared
 	When user enters "<invalid_email>" data
 	Then user verifies the error message "Please enter a valid email address."
 	When user enters "<valid_email>" data
 	Then the verified tick symbol is displayed
 	When user restarts the conversation
 	Then the email id field displayed again
	
  Examples:
      | invalid_email	| valid_email			|
      |	123456				| test@test1.com	|
      
      
  
  @chat
  Scenario Outline: Chat bot Positive flow
  
  Given user opens the chatbot
  Then the email field should be appeared
 	When user enters "<email>" data
 	Then the verified tick symbol is displayed
 	And the first name field should be appeared
 	When the user enters firstname "<first_name>"
 	Then the chat prompts to select the country
 	When the user selects the country "<country>"
 	Then the thank you message "<message>" is displayed
 	
  Examples:
  |email					|first_name |country|	message	|
  |test@test1.com	|test				|India	|Thank you for sharing the details! We will get back to you shortly.|
      
      
      
  @chat
  Scenario: Close and open the chatbot and check the previous conversation is exists
  
  Given user opens the chatbot
  Then the email field should be appeared
 	When user enters "test@test1.com" data
 	Then the verified tick symbol is displayed
 	When user closes the chatbot
 	And user clicks the chatbot
  Then the chatbox opens
 	And user checks the previous conversation is exist

	
	
	@chat
	Scenario: Navigate to different tab then return to chatbot and enter data
  
  Given user opens the chatbot
  When user goes to new "https://www.google.com/" tab
  And the user returns to chatbot
  Then the email field should be appeared
 	When user enters "test@test1.com" data
 	Then the verified tick symbol is displayed