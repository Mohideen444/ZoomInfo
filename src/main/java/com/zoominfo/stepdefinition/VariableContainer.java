package com.zoominfo.stepdefinition;

public class VariableContainer {
    
	//POJO class to maintain the runtime variables using get and set methods.
	//implemented in step definitions file using the pico-container library
	
	private String email;
	private String name;
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
