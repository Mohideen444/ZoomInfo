package com.zoominfo.stepdefinition;

import com.zoominfo.setup.BaseSetup;
import com.zoominfo.setup.BaseSetup.BaseSetupAlreadyInitialized;
import com.zoominfo.setup.GlobalConfiguration.EmptyGlobalConfigException;
import com.zoominfo.setup.GlobalConfiguration.UnInitialzedGlobalConfig;
import com.zoominfo.setup.WebDriverSetup;

import io.cucumber.java.*;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.*;
import java.awt.*;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;

// Hooks are blocks of code that run before and after each scenario
// precondition and teardown of scenarios maintained here

public class Hooks {
    private Logger logger = LoggerFactory.getLogger(Hooks.class);
    public static Scenario scenario;
    public LocalDateTime localDateTime_beforeStep;
    public LocalDateTime localDateTime_afterStep;

    public static Instant startTime;
    public static Instant endTime;

    @Before
    public void init(Scenario scenario) throws IOException, EmptyGlobalConfigException, UnInitialzedGlobalConfig, AWTException {
        Hooks.scenario = scenario;
        Hooks.scenario.log("scenario_start_timestamp: " + LocalDateTime.now());
        BaseSetup.init();
        try {
            BaseSetup.getInstance().initBrowser();
        } catch (BaseSetupAlreadyInitialized e) {
            logger.info("Base Setup Already Initialized");
            e.printStackTrace();
        }
        logger.info("Base Setup is initialized ");

    }
    
    @AfterStep
    public void attach_screenshot(Scenario scenario)
    {
    	if (scenario.isFailed()) {
    		final byte[] screenshot = ((TakesScreenshot)WebDriverSetup.getInstance().getDriver()).getScreenshotAs(OutputType.BYTES);
    		scenario.attach(screenshot, "image/png", "error "+LocalDateTime.now());
    	}
    }

    @After
    public void tearDown(Scenario scenario) {
        scenario.log("scenario_end_timestamp: " + LocalDateTime.now());
        System.out.println("After Method");
        WebDriverSetup.getInstance().quitWebDriver();

    }

    public static void main(String[] arg) {
        System.out.print(System.getProperty("user.dir") + "/Report/Screenshots/");
    }
    
}



