package framework.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.NoSuchElementException;


import static framework.WebDriverManager.getDriver;

public class Wait {
    private static final Logger LOG = LoggerFactory.getLogger(Wait.class);
    private static Wait instance;

    private Wait() {
    }

    public static Wait getInstance() {
        return new Wait();
    }

    /**
     * Stops program execution for specified amount of time.
     *
     * @param milliseconds Amount of time to waitFor in milliseconds (1000 = 1 second)
     */
    public void waitFor(int milliseconds) {
        waitFor((long) milliseconds, true);
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
     * Method which will execute given javascript and return th result.
     */
    private Object executeJS(String jsScript) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(jsScript);
    }

    /**
     * An expectation for checking if DOM 'document.readyState' to become 'complete'.
     *
     * @return true once the element contains the given text
     */
    public ExpectedCondition<Boolean> jsLoad() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return executeJS("return document.readyState").toString().equals("complete");
            }

            @Override
            public String toString() {
                return "DOM 'document.readyState' to become 'complete'";
            }
        };
    }

    /**
     * An expectation for checking if DOM 'document.readyState' to become 'complete'.
     *
     * @return true once the element contains the given text
     */
    public ExpectedCondition<Boolean> ajaxFinished() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((Long) executeJS(
                        "if (window.jQuery != undefined) { return jQuery.active;} " +
                                "else if (window.Backbone != undefined) { return Backbone.$.active; } " +
                                "else { return 0; }") == 0);
            }

            @Override
            public String toString() {
                return "ajax processes are finished";
            }
        };
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

    public void waitBeforeFileDownloads(File folder, boolean withSpaces, String fileName) {
        waitFor(2000);
        File file = null;
        double fileSize = 0;
        double iterations;
        if (withSpaces) { //For some reason files with spaces('%20' - encoded space) takes too much time to download
            iterations = 6;
        } else {
            iterations = 3;
        }
        for (int i = 0; i < iterations; i++) {
            try {
                if (fileName == null) {
                    file = folder.listFiles()[0];
                    fileSize = file.length();
                } else {
                    if (Arrays.stream(folder.listFiles()).map(file1 -> file1.getName()).anyMatch(name -> name.equals(fileName)))
                        break;
                    else {
                        throw new NullPointerException("The file still won't load.. Need to wait more");
                    }
                }
            } catch (NullPointerException | ArrayIndexOutOfBoundsException ex) {
                LOG.info("The file still won't load.. Need to wait more");
            }
            if (fileSize == 0 || file.getName().contains("download")) {
                LOG.info("File still downloading...");
                waitFor(10_000);
            }
        }
    }

    public By waitFirstElementVisible(By... elements) {
        int counter = 0;
        FluentWait<WebDriver> fluentWait = new FluentWait<>(getDriver())
                .pollingEvery(Duration.ofMillis(250))
                .withTimeout(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        while (counter++ < 5) {
            for (By element : elements)
                try {
                    LOG.debug(String.format("Waiting for %s, timeout: %d seconds", ExpectedConditions.visibilityOfElementLocated(element), 5));
                } catch (TimeoutException ignored) {}
        }
        throw new TimeoutException("No one from two elements wasn't found");
    }

    public boolean isVisible(By locator) {
        try {
           if(getDriver().findElement(locator).isDisplayed()) {
               return true;
           }
        } catch(Exception ex) {
            LOG.debug(String.format("Element is not visible", ex));
        }
        return false;
    }

}