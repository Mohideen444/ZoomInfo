Feature: zoom info chatbot testing

  @chat @staging
  Scenario: Open and perform initial validations in the chatbot
  
  Given user navigates to the assignment webpage
  And user verifies the welcome message
  When user clicks the chatbot
 	Then the chat window opens with the welcome message
    

    #Examples:
      #| email		| first_name 	| country | 
      #| 123546	| test			  | India		|
