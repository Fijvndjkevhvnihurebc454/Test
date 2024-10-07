package framework.base;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static framework.WebDriverManager.getDriver;

public class BasePage {
    public static final Logger LOG = LoggerFactory.getLogger(BasePage.class);
    protected Wait wait;
    private static ThreadLocal<String> windowHandleThreadLocal = new ThreadLocal<>();

    public static String getWindow() { return windowHandleThreadLocal.get(); }

    public static void removeWindow() { windowHandleThreadLocal.remove(); }

    public static void setWindow(String window) { windowHandleThreadLocal.set(window); }

    public BasePage() {
        wait = Wait.getInstance();
    }

    public boolean isVisible(By by, int timeOutSeconds) {
        LOG.info(String.format("Checking if element visible located: %s", by));
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by), timeOutSeconds) != null;
        } catch (NoSuchElementException | TimeoutException ex) {
            LOG.debug(String.format("Couldn't find element located by: %s", by));
            return false;
        }
    }

    public boolean isVisible(By by) { return isVisible(by, 0); }

    protected boolean allAreVisible(By by, int timeOut) {
        LOG.info(String.format("Checking if all elements are visible located: %s", by));
        try {
            return !wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by), timeOut).isEmpty();
        } catch (StaleElementReferenceException ex) {
            LOG.error("Some element staled, re-trying");
            return allAreVisible(by, timeOut);
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public void clickOnElement(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by), 2);
            findWebElement(by).click();
        } catch (ElementNotVisibleException | TimeoutException ex) {
            LOG.info(String.format("Element is not clickable with locator: %s", by));
        }
    }

    public void clickOnElementWithWait(By by, int time) {
        try {
            wait.waitFor(time);
            findWebElement(by).click();
        } catch (ElementNotVisibleException | TimeoutException ex) {
            LOG.info(String.format("Element is not clickable with locator: %s", by));
       }
    }

    public void scrollDownToElement(WebElement element) {
        try {
            wait.waitFor(3_000);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView(false)", element);
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info(String.format("Cant scroll to element: %s", element));
        }
    }

    public void scrollDown() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("scroll(0, 500);");
            wait.waitFor(2_000);
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant scroll down");
        }
    }

    public void moveToElementAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element), 2);
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element).click(element).perform();
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info(String.format("Cant scroll to element: %s", element));
        }
    }

    public WebElement findWebElement(By by) {
        try {
            return getDriver().findElement(by);
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info(String.format("Element is not found with locator: %s", by));
            return null;
        }
    }

    public List<String> getAllTextsFromElement(By by) {
        try {
            return getDriver().findElements(by).stream()
                    .map(el -> el.getText().trim())
                    .collect(Collectors.toList());
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info(String.format("Elements are not found with locator: %s", by));
            return Collections.emptyList();
        }
    }

    public Set<String> getWindowHandles() {
        try {
            return getDriver().getWindowHandles();
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Windows are not opened");
            return Collections.emptySet();
        }
    }

    public String getWindowHandle() {
        try {
           return getDriver().getWindowHandle();
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Window is not opened");
            return null;
        }
    }

    public void switchToWindow(String windowId) {
        try {
            getDriver().switchTo().window(windowId);
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Window is not opened");
        }
    }

    public void navigateTo(String url) {
        try {
            getDriver().navigate().to(url);
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info(String.format("Cant navigate to url: %s", url));
        }
    }

}
