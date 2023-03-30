package com.zoominfo.setup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import com.zoominfo.enums.ProjectStructure;
import com.zoominfo.library.FileHandler;
import com.zoominfo.library.IScreenRecorder;
import com.zoominfo.library.MonteScreenRecorder;
import com.zoominfo.setup.GlobalConfiguration.EmptyGlobalConfigException;
import com.zoominfo.setup.GlobalConfiguration.UnInitialzedGlobalConfig;

import java.awt.*;
import java.io.IOException;

public class BaseSetup {
	
	//Initializer package - All the framework setups are initialized here

    private GlobalPaths globalPaths;
    private FileHandler fileHandler = null;
    private static BaseSetup baseSetup = null;

    private GlobalConfiguration globalConfiguration;
    private WebDriverSetup webDriverSetup;
    private IScreenRecorder screenRecorder;

    private static Logger logger = LoggerFactory.getLogger(BaseSetup.class);
    private static Marker fatal = MarkerFactory.getMarker("FATAL");

    private BaseSetup() {

    }


    public static final void init() throws IOException, EmptyGlobalConfigException, UnInitialzedGlobalConfig, AWTException {
        if (baseSetup == null) {
            BaseSetup.baseSetup = new BaseSetup();
            baseSetup.fileHandler = new FileHandler();

            //baseSetup.globalConfiguration.setGlobalConfig();

            //Initialize Global Configuration
            GlobalConfiguration.init();
            baseSetup.globalConfiguration = GlobalConfiguration.getInstance();

            //Init Path
            baseSetup.globalPaths = new GlobalPaths();

            //baseSetup.createBackupIfFilesExist();
            baseSetup.checkDirectoryStructure();


            //Initialize WebDriver Setup
            WebDriverSetup.init();
            baseSetup.webDriverSetup = WebDriverSetup.getInstance();

            //Initialize ScreenRecorder
            baseSetup.screenRecorder = new MonteScreenRecorder(baseSetup.globalPaths.path_to_screenrecording);


        } else
            logger.info("BaseSetup Already Initialized");

    }

    public void initBrowser() {
        String browser;
        int wait;
        int explicit_wait;
        browser = globalConfiguration.getBrowser();
        wait = globalConfiguration.getWait_time();
        explicit_wait = globalConfiguration.getExplicit_wait_time();
        webDriverSetup.invokeBrowser(browser, wait, explicit_wait);
    }

    public IScreenRecorder getScreenRecorder() {
        return screenRecorder;
    }


    public static BaseSetup getInstance() throws BaseSetupAlreadyInitialized {
        if (baseSetup != null)
            return baseSetup;
        else {
            logger.info("BaseSetup is not Initialized");
            throw new BaseSetupAlreadyInitialized("Base Setup is not Initialized Please Initialize it ");
        }

    }

    private void checkDirectoryStructure() {
        String fileSeparator = globalPaths.getFile_separator();
        // Create log folder if they are not present
        fileHandler.createDirectoryIfDoesntExist(ProjectStructure.pathToProject.toString()
                + fileSeparator
                + ProjectStructure.logs.toString());

        //Create report folder if not present
        fileHandler.createDirectoryIfDoesntExist(ProjectStructure.pathToProject.toString()
                + fileSeparator
                + ProjectStructure.report.toString());

        //Create screenshot folder inside report
        fileHandler.createDirectoryIfDoesntExist(ProjectStructure.pathToProject.toString()
                + fileSeparator
                + ProjectStructure.report.toString()
                + fileSeparator
                + ProjectStructure.screenshots.toString());

        fileHandler.createDirectoryIfDoesntExist(ProjectStructure.pathToProject.toString()
                + fileSeparator
                + ProjectStructure.uploads.toString());

        fileHandler.createDirectoryIfDoesntExist(ProjectStructure.pathToProject.toString()
                + fileSeparator
                + ProjectStructure.downloads.toString());

        fileHandler.createDirectoryIfDoesntExist(globalPaths.path_to_uploads);

        //Exit program if feature folder are not present
        if (!fileHandler.checkDirectoryExist(ProjectStructure.pathToProject.toString()
                + fileSeparator
                + ProjectStructure.feature.toString())) {
            logger.error(fatal, "Missing: " + ProjectStructure.feature.toString());
            System.exit(0);
        }

    }


    public static class BaseSetupException extends RuntimeException {
        BaseSetupException(String get_message) {
            super(get_message);
        }
    }

    public static class BaseSetupAlreadyInitialized extends BaseSetupException {
        public BaseSetupAlreadyInitialized(String get_message) {
            super(get_message);
        }

    }
    
}

