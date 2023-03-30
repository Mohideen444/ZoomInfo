package com.zoominfo.getters;

import java.io.IOException;
import org.json.simple.parser.ParseException;

import com.zoominfo.enums.ProjectStructure;
import com.zoominfo.library.FileHandler;
import com.zoominfo.library.JSONFileHandler;
import com.zoominfo.setup.GlobalConfiguration;
import com.zoominfo.setup.GlobalPaths;

public class UrlInfo {
	
	
	//Reading test data from the json files using jsonFileHandler library under com.zoominfo.library package
	
	GlobalPaths globalPaths = new GlobalPaths();
	
	String zinfo_url_path = ProjectStructure.pathToProject.toString()
			+globalPaths.getFile_separator()
			+ProjectStructure.testdata.toString()
			+globalPaths.getFile_separator()
			+globalPaths.getEnv()
			+globalPaths.getFile_separator()
			+"zinfo-urls.json";

	
	public String getZinfoUrls(String url_key) throws FileHandler.FileHandlerException.FileIsNotPresent, IOException, ParseException
	{
		return new JSONFileHandler(zinfo_url_path)
				.parseObject("zinfo")
				.parseObject(url_key).toString();
	}
	
	
	public static void main(String[] args) throws FileHandler.FileHandlerException.FileIsNotPresent, IOException, ParseException {
		GlobalConfiguration.init();
		UrlInfo u = new UrlInfo();
		String s = u.getZinfoUrls("main").toString();
		System.out.println("The URL is :" + s);
	}

}