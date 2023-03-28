package com.zoominfo.setup;

import com.zoominfo.enums.Environment;
import com.zoominfo.enums.FileSeparator;
import com.zoominfo.enums.OS;
import com.zoominfo.enums.ProjectStructure;

public class GlobalPaths {

    private GlobalConfiguration globalConfiguration = GlobalConfiguration.getInstance();
    private String file_separator = translateFileSeparator();

    private Environment env = globalConfiguration.getEnvironment();

    public final String path_to_project = ProjectStructure.pathToProject.toString();

    public final String path_to_report = path_to_project
        +file_separator
        +ProjectStructure.report
        +file_separator;

    public final String path_to_log = path_to_project
            +file_separator
            +ProjectStructure.logs
            +file_separator;

    public final String path_to_testdata = ProjectStructure.pathToProject.toString()
            + file_separator
            + ProjectStructure.testdata.toString()
            + file_separator
            +env
            +file_separator;
    
    public final String path_to_downloads = path_to_project
            +file_separator
            +ProjectStructure.downloads
            +file_separator;
    
    public final String path_to_uploads = path_to_project
            +file_separator
            +ProjectStructure.uploads
            +file_separator;
    
    public final String path_to_screenrecording = path_to_report
            + ProjectStructure.screenrecording
            +file_separator;
    
    public final String path_to_screenshot = path_to_report
            +ProjectStructure.screenshots
            +file_separator;

    public GlobalPaths()
    {
       translateFileSeparator();
    }
    public static FileSeparator translateFileSeparator(OS os)
    {
       switch (os)
       {
           case WINDOWS:
               return FileSeparator.WINDOWS;
           case LINUX:
               return FileSeparator.LINUX;
           default:
               throw new RuntimeException("Invalid file separator");
       }

    }

    public String translateFileSeparator()
    {
      return translateFileSeparator
              (GlobalConfiguration.getInstance().getOs())
              .toString();
    }

    public  String getFile_separator() {
        return file_separator;
    }

    public Environment getEnv() {
        return env;
    }

    public static void main(String[] args)
   {
       System.out.println(System.getProperty("os.name"));
    }
}
