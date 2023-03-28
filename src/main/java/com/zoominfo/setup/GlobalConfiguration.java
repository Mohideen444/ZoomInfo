package com.zoominfo.setup;

import org.slf4j.*;

import com.zoominfo.enums.Browser;
import com.zoominfo.enums.Environment;
import com.zoominfo.enums.OS;

public class GlobalConfiguration {
    private int wait_time = 15;
    private int explicit_wait_time = 240;
    protected String environment;
    private String browser;
    protected Environment env_enum;
    protected OS os;
    protected Browser browser_enum;

    private static GlobalConfiguration globalConfiguration = null;

    private static Logger logger = LoggerFactory.getLogger(GlobalConfiguration.class);

    private GlobalConfiguration() {
    }

    public int getWait_time() {
        return wait_time;
    }

    public int getExplicit_wait_time() {
        return explicit_wait_time;
    }

    public Environment getEnvironment() {
        return env_enum;
    }

    public String getBrowser() {
        return browser;
    }

    public OS getOs() {
        return os;
    }

    public static void init() throws EmptyGlobalConfigException {
        if (globalConfiguration == null) {
            globalConfiguration = new GlobalConfiguration();
            //globalConfiguration.setGlobalConfig();
            globalConfiguration.overrideGlobalConfig();
            globalConfiguration.translateGlobalConfigToEnum();
            globalConfiguration.translateOS();

        } else {
            logger.info("Global Configuration is already initialized");
        }
    }

    public static GlobalConfiguration getInstance() throws UnInitialzedGlobalConfig {
        if (globalConfiguration != null) {
            return globalConfiguration;
        } else
            throw new UnInitialzedGlobalConfig("Please initialize global configuration");
    }

    public void setGlobalConfig() throws EmptyGlobalConfigException {
        setEnvironment();
        setBrowser();
    }


    public void setEnvironment() {
        setEnvironment(System.getProperty("testenvironment"));
    }

    public void setBrowser() {
        setBrowser(System.getProperty("browser"));
    }

    public void setEnvironment(String env) {
        environment = env;
        if (environment.isEmpty()) {
            logger.info("Environmet variable is Empty");
            throw new EmptyGlobalConfigException("Env is empty");
        }
        logger.info("environment : " + environment);
    }

    public void setBrowser(String brow) {
        browser = brow;
        logger.info("browser : " + browser);
        if (browser.isEmpty()) {
            logger.info("Browser variable is Empty");
            throw new EmptyGlobalConfigException("Browser variable is Empty");
        }
    }

    public void overrideGlobalConfig() throws EmptyGlobalConfigException {
        logger.info("Global Config over ridden :");
        setEnvironment("staging");
        logger.info("environment : " + environment);
        setBrowser("chrome");
        logger.info("browser : " + browser);
    }

    public Environment translateEnvironment(String env) {
        switch (env) {
            case "production":
                return Environment.PRODUCTION;
            case "staging":
                return Environment.STAGING;
            default:
                throw new InvalidEnvironmentException("Env Provided-" + env + "\n Must be staging/production");
        }
    }

    public Browser translateBrowser(String browser) {
        switch (browser) {
            case "chrome":
                return Browser.CHROME;
            default:
                throw new InvalidBrowserException("Invalid Browser" + browser);
        }
    }

    public void translateGlobalConfigToEnum() {
        env_enum = translateEnvironment(environment);
        browser_enum = translateBrowser(browser);
    }

    public OS translateOS(String os) {
        switch (os) {
            case "Windows 10":
                return OS.WINDOWS;
            case "Linux":
                return OS.LINUX;
            default:
                throw new RuntimeException("Invalid OS" + os);
        }
    }

    public void translateOS() {
        String os;
        os = System.getProperty("os.name");
        this.os = translateOS(os);
    }

    public class EmptyGlobalConfigException extends RuntimeException {

        public EmptyGlobalConfigException(String message) {
            super(message);
            // TODO Auto-generated constructor stub
        }

    }

    public static class UnInitialzedGlobalConfig extends RuntimeException {

        public UnInitialzedGlobalConfig(String message) {
            super(message);
            // TODO Auto-generated constructor stub
        }

    }

    public static class InvalidEnvironmentException extends RuntimeException {
        public InvalidEnvironmentException(String message) {
            super(message);
        }
    }

    public class InvalidBrowserException extends RuntimeException {
        public InvalidBrowserException(String message) {
            super(message);
        }
    }
}