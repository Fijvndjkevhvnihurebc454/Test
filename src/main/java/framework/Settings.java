package framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Settings {

    public static WebDriver getDriverInstance(String browserType) {
        if(browserType.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver(setupChromeOptions());
        } else if(browserType.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        }
        return new ChromeDriver(setupChromeOptions());
    }

    private static ChromeOptions setupChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--enable-automation");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--ignore-ssl-errors");
        chromeOptions.addArguments("enable-features=NetworkServiceInProcess");
        chromeOptions.setCapability("download.prompt_for_download", false);
        chromeOptions.setCapability("download.directory_upgrade", true);
        chromeOptions.setCapability("safebrowsing.enabled", false);
        chromeOptions.setAcceptInsecureCerts(true);
        return chromeOptions;
    }

    public static boolean driverEnabled(WebDriver driver) {
        return driver != null && !driver.toString().contains("null");
    }

}
