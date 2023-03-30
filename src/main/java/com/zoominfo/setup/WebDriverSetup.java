package com.zoominfo.setup;


import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.slf4j.*;

import com.zoominfo.library.WebDriverCommons;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSetup{
	
	//All the webdriver related configurations were made here
	
	private  String browser;
	private  int implicit_wait;
	private  int explicit_wait;
	protected WebDriver original_driver;
    protected  WebDriver driver;
    protected WebDriverCommons webDriverCommons;
    private static WebDriverSetup webDriverSetup = null;
    private static Logger logger = LoggerFactory.getLogger(WebDriverSetup.class);
    private  Marker fatal = MarkerFactory.getMarker("FATAL");

	private GlobalPaths globalPaths = new GlobalPaths();
    String download_path = globalPaths.path_to_downloads;

    public WebDriver getDriver() {
        return driver;
    }
    
    protected  void WebDriversSetup() {
    	
	}
    
    public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public int getImplicit_wait() {
		return implicit_wait;
	}

	public void setImplicit_wait(int implicit_wait) {
		this.implicit_wait = implicit_wait;
	}
    
	public int getExplicit_wait() {
		logger.info("explicit wait"+explicit_wait);
		return explicit_wait;
	}

	public void setExplicit_wait(int explicit_wait) {
		this.explicit_wait = explicit_wait;
	}
	
	public WebDriver getWebDriver()
	{	if(driver != null)
			return driver;
		else
			throw new RuntimeException ("Please initialize WebDriveSetup");
	}
	
	public WebDriverCommons getWebDriverCommons()
	{	if(webDriverCommons != null)
			return webDriverCommons;
		else
			throw new RuntimeException ("Please initialize webDriverCommons is not initialized");
	}
	
    public String getUrl()
    {
        return driver.getCurrentUrl();
    }
    public static void init()
	{
		if (webDriverSetup == null)
		{
			webDriverSetup = new WebDriverSetup();
		}
		else 
		{
			logger.info("Web Driver is already initialized");
		}
	}
    public static WebDriverSetup getInstance()
    {
    	if(webDriverSetup != null)
    	{
    		return webDriverSetup;
    	}
    	else
    	{
    		throw new RuntimeException ("Please initialize WebDriveSetup");
    		
    	}
    }
 	private void initializeWebDriver(String browser)
   	{	//String driver_path;
   		switch (browser) 
   		{
   			case "chrome":
   				WebDriverManager.chromedriver().setup();
   				original_driver = new ChromeDriver(setChromeOptions());
   				/*driver_path = new File("Driver/chromedriver.exe").getAbsolutePath();
   				System.setProperty("webdriver.chrome.driver", driver_path);
   				original_driver = new ChromeDriver();
   				logger.info("Web Driver Initialized");*/
   				break;
   			case "firefox":
   				break;
   			default:
   				logger.error(fatal,"no browser selected");
   				System.exit(0);
   		}
   	}
 	
 	public ChromeOptions setChromeOptions()
 	{	
 		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
 		chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", download_path);
        
 		ChromeOptions chromeOptions =new ChromeOptions();
 		//chromeOptions.addArguments("start-maximized");
 		//chromeOptions.addArguments("--headless");     
        chromeOptions.addArguments("--disable-gpu");
 		chromeOptions.addArguments("--window-size=1920,1200");
 		chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.addArguments("--remote-allow-origins=*");
		//chromeOptions.setHeadless(true);
		return chromeOptions;
 	}
 	
 	private void initializeEventListner()
	{
		WebDriverListner listner = new WebDriverListner();
		EventFiringDecorator event_decorator;
		event_decorator = new EventFiringDecorator(listner);
		driver = event_decorator.decorate(original_driver);
	}
 	
 	protected void reloadPage()
    {
        driver.navigate().refresh();
        logger.info("Page Reloaded");
    }
 	
	public void invokeBrowser(String browser,int implicit_wait,int explict_wait)
	{  
		try {
			setBrowser(browser);
			setImplicit_wait(implicit_wait);
			setExplicit_wait(explict_wait);
		 	initializeWebDriver(browser);
		 	initializeEventListner();
		    logger.info("Driver Initialized");
		    maximizeWindow();
		    webDriverCommons= new WebDriverCommons(driver);
			setPageLoadTimeout();

	        }
	     catch (NullPointerException e)
	      {
	         e.printStackTrace();
	      }
	 }
	
 	 public void setImplicitWait(int wait)
 	 {	
 		webDriverCommons.setImplicitWait(wait); 
 		webDriverCommons.turnOnImplicitWait(wait);
 		logger.info("Implicit Wait Applied: "+wait+" Secs");
 	 }
 	 
 	 public void setPageLoadTimeout()
	 {
		 webDriverCommons.setPageLoadTimeout(explicit_wait);
	 }

 	 protected void maximizeWindow()
 	 {
 		driver.manage().window().maximize();
 	 }
 	 
 	 public void quitWebDriver()
 	 {
 		 driver.quit();
 	 }
 



}