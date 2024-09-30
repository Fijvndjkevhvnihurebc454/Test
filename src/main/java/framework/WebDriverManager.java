package framework;

import framework.base.Configuration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WebDriverManager {
    private static final Logger LOG = LoggerFactory.getLogger(WebDriverManager.class);

    private static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() { return driverThreadLocal.get(); }

    public static void setDriver(WebDriver driver) { driverThreadLocal.set(driver); }

    public static void setPageLoadTimeout(int timeout) {
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
    }

    public static void setScriptTimeout(int timeout) {
        getDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(timeout));
    }

    public static void maximizeWindow() {
       getDriver().manage().window().maximize();

    }

    public static void setWindowSize(int width, int height) {
        getDriver().manage().window().setSize(new Dimension(width, height));
    }

    public static void startBrowser() {
        quitDriver();
        initAndSetupDriver();
    }

    public static String getBrowserType() {
        try {
            if(Configuration.getBrowser().equals("") || Configuration.getBrowser().equals(null)) {
                LOG.info(String.format("Getting browser type %s from command line", System.getProperty("browser")));
                return System.getProperty("browser");
            } else {
                return Configuration.getBrowser();
            }
        } catch (Exception ex) {
            throw new IllegalArgumentException("Browser type is not set");
        }
    }

    private static void initAndSetupDriver() {
        WebDriverManager.setDriver(Settings.getDriverInstance(getBrowserType()));
        WebDriverManager.setPageLoadTimeout(15);
        WebDriverManager.setScriptTimeout(60);
        WebDriverManager.maximizeWindow();
    }

    public static void quitDriver() {
        if (Settings.driverEnabled(WebDriverManager.getDriver())) {
            WebDriverManager.getDriver().quit();
        }
    }

}
