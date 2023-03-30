package com.zoominfo.library;



import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.zoominfo.library.FileHandler.FileHandlerException.FileIsNotPresent;

import java.io.*;


public class FileHandler {
	
	// File Handler class created for handling all type of files. 
	//Created some common methods like reading a file, creating and deleting a directory
	
	protected File file;
	static Logger log = LoggerFactory.getLogger(FileHandler.class);
	static Marker fatal = MarkerFactory.getMarker("FATAL");

	
	public FileHandler()
	{
		
	}
	
	public FileHandler(String file_path) throws FileIsNotPresent
	{
		if(!checkDirectoryExist(file_path))
		{
			throw new FileIsNotPresent("Please Mention correct file path : \n"+file_path);
		}
		else
		{
			file = new File(file_path);
		}
	}	
	
	public FileReader readFile() throws FileNotFoundException
	{
		FileReader fileReader = new FileReader(file);
		return fileReader;
	}
	
	public static boolean checkDirectoryExist(String directory_path)
	{	
		File file;
		file = new File  (directory_path);
		
		if(file.exists())
		{	
			log.info("Directoy Exist"+directory_path);
			return true;
		}
		else
			log.info("Directoy Doesnt Exist"+directory_path);
			return false;		
	}
	
	public void createDirectoryIfDoesntExist(String directory_path)
	{	
		File file;
		file = new File(directory_path);
		
		if(!checkDirectoryExist(directory_path))
		{
			file.mkdir();
			log.info("Directoy Created "+directory_path);
		}	
		
	}
	

	public static boolean deleteDirectory(String path)
	{	File file;
		if(checkDirectoryExist(path))
		{
			file = new File(path);
			try {
				FileUtils.deleteDirectory(file);
				log.info("file deleted : "+path);
				return true;
			} catch (IOException e) {
				log.error(fatal,ExceptionUtils.getMessage(e));
				e.printStackTrace();
				return false;
			}
		}
		else
			return false;
	}
	
	
	public boolean cleanDirectory(String path) throws IOException
	{	File file;
		if(checkDirectoryExist(path))
		{
			file = new File(path);
			FileUtils.cleanDirectory(file);
			log.info("files inside directory deleted : "+path);
			return true;
			
		}
		else
			return false;
	}
	
	//Exception classes
	
	
	public  static class FileHandlerException extends RuntimeException
	{
		public FileHandlerException(String ex_message) {
		super(ex_message);
	}
		
		
	public  static class FileIsNotPresent extends RuntimeException
		{
			public FileIsNotPresent(String ex_message) {
				super(ex_message);
			}
			
		}
	}
	
}
