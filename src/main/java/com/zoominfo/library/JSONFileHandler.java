package com.zoominfo.library;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.*;

import com.zoominfo.library.FileHandler.FileHandlerException.FileIsNotPresent;


public class JSONFileHandler {
	
	//This library class created using "json.simple" library from google.
	//The following are common functions to handle the json file
	
	private FileReader fileReader = null;
	private Object parsed_object = null;
	
	private static Logger log = LoggerFactory.getLogger(JSONFileHandler.class);
	
	public JSONFileHandler(String file_path) throws FileIsNotPresent, IOException, ParseException {
		readFile(file_path);
		parseJson();
	}
	
	private JSONFileHandler(Object object) throws FileIsNotPresent, IOException, ParseException {
		this.parsed_object = object;
	}
	
	private void readFile(String file_path) throws FileIsNotPresent, FileNotFoundException
	{	
		FileHandler fileHandler;
		fileHandler = new FileHandler(file_path);
		fileReader = fileHandler.readFile();
	}

	
	public Object getObject() {
		log.info("parsed object : "+ parsed_object);
		return parsed_object;
	}
	
	public JSONFileHandler parseJson() throws IOException, ParseException
	{	
		JSONParser parser = new JSONParser();
		parsed_object = parser.parse(fileReader);
		log.info("parsed object : "+parsed_object.toString());
		log.info("JSON Parsed");
		return this;
	}
	
	public JSONFileHandler parseObject(String key) throws FileIsNotPresent, IOException, ParseException
	{	
		JSONObject jsonObject;
		Object object;
		jsonObject = (JSONObject)parsed_object;
		object = jsonObject.get(key);
		log.info("JSON Object : "+object.toString()+"\n Key "+ key);
		return new JSONFileHandler(object);
	}
	
	public JSONFileHandler parseArray(int index) throws FileIsNotPresent, IOException, ParseException
	{	
		JSONArray jsonArray;
		Object object;
		jsonArray = (JSONArray) parsed_object;
		object = jsonArray.get(index);
		log.info("JSONArray" + object.toString());
		return new JSONFileHandler(object);
	}
	
	public String toString()
	{
		return parsed_object.toString();
	}
}
