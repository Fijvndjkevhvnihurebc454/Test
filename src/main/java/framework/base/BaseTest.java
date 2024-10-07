package framework.base;

import com.nordstrom.automation.testng.LinkedListeners;
import framework.listener.WebDriverListener;
import org.openqa.selenium.By;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.time.Duration;

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
            try {
                Configuration.getResourcesFromPropertyFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
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
