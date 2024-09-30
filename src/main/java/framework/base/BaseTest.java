package framework.base;

import com.nordstrom.automation.testng.LinkedListeners;
import framework.listener.WebDriverListener;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

import static framework.WebDriverManager.*;
import static framework.WebDriverManager.getDriver;

@LinkedListeners({
     WebDriverListener.class
})
public class BaseTest {
    BasePage basePage = new BasePage();

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        quitDriver();
        Configuration.getResourcesFromPropertyFile();
        startBrowser();
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().to(Configuration.getUrl());
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        clickAcceptCookies();
    }

    @AfterMethod(alwaysRun = true)
    public void afterAll(ITestResult result) {
        new WebDriverListener().onTestFailure(result);
        quitDriver();
    }

    private void clickAcceptCookies() {
        if(Wait.getInstance().isVisible(By.cssSelector(".wt-cli-accept-all-btn"))) {
            basePage.clickOnElement(By.cssSelector(".wt-cli-accept-all-btn"));
        }
    }
}
