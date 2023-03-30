package com.zoominfo.enums;

public enum Environment 
{
	STAGING("staging")
	,PRODUCTION("production")
	,QA("qa");
	
	public String value;
	
	Environment(String env)
	{
		this.value = env;
	}
	
	public String toString()
	{
		return this.value.toString();
	}
	
}
