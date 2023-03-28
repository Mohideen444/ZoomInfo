package com.zoominfo.runner;



import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.json.simple.parser.ParseException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.zoominfo.setup.BaseSetup;
import java.io.IOException;

@CucumberOptions(plugin = {
        "html:target/selenium-reports",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = {"Feature"},
        glue = {"com.zoominfo"},
        
        tags = "@chat and @staging",

        monochrome = true,
        dryRun = false
)

public class CucumberRunner extends AbstractTestNGCucumberTests {

    @BeforeClass
    public static void startup() {
        try {
            BaseSetup.init();
            BaseSetup.getInstance().getScreenRecorder().startScreenRecording();
        } catch (Exception e) {
            System.out.println("At Start up Exception" + e.getMessage());
            System.exit(1);
        }
    }

    @AfterClass
    public static void tearDown() throws IOException, ParseException, InterruptedException {

        BaseSetup.getInstance().getScreenRecorder().stopScreenRecording();


        }
}
