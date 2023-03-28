package com.zoominfo.enums;

public enum ProjectStructure {
	pathToProject(System.getProperty("user.dir"))
	,feature("Feature")
	,report("Report")
	,logs("Logs")
	,screenshots("Screenshots")
	,screenrecording("ScreenRecording")
	,fileSeperator("\\")
	,testdata("Testdata")
	,downloads("Downloads")
	,uploads("Uploads");

	private String value;

	ProjectStructure(String value)
	{
		this.value = value;
	}

	public String toString()
	{
		return this.value;
	}
}
