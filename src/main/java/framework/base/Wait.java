package framework.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static framework.WebDriverManager.getDriver;

public class Wait {
    private static final Logger LOG = LoggerFactory.getLogger(Wait.class);

    private Wait() { }

    public static Wait getInstance() {
        return new Wait();
    }

    /**
     * Stops program execution for specified amount of time.
     *
     * @param milliseconds Amount of time to waitFor in milliseconds (1000 = 1 second)
     */
    public void waitFor(int milliseconds) {
        waitFor(milliseconds, true);
    }

    public void waitFor(long milliseconds, boolean withLog) {
        if (withLog) {
            LOG.info(String.format("Stopping test execution for %d milliseconds", milliseconds));
        }
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns instance of WebDriverWait.
     */
    private WebDriverWait getWebDriverWait(int timeout) {
        WebDriver driver = getDriver();
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    /**
     * Calling {@code waitFor} with default timeout.
     */
    public <T> T until(ExpectedCondition<T> condition) {
        return until(condition, 20);
    }

    public <T> T until(ExpectedCondition<T> condition, int timeoutSeconds) {
        return until(condition, timeoutSeconds, false);
    }

    public <T> T until(ExpectedCondition<T> condition, int timeoutSeconds, boolean log) {
        if (log)
            LOG.info(String.format("Waiting for %s, timeout: %d seconds", condition, timeoutSeconds));
        return getWebDriverWait(timeoutSeconds).ignoring(StaleElementReferenceException.class).until(condition);
    }

    public boolean isVisible(By locator) {
        try {
           if(getDriver().findElement(locator).isDisplayed()) {
               return true;
           }
        } catch(Exception ex) {
            LOG.debug(String.format("Element is not %s", ex));
        }
        return false;
    }

}