package framework.base;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static framework.WebDriverManager.getDriver;

public class BasePage {
    public static final Logger LOG = LoggerFactory.getLogger(BasePage.class);
    public static int WAIT_TIMEOUT_SECONDS = 20;
    public static final int SHORT_WAIT_TIMEOUT_SECONDS = 5;
    protected Wait wait;
    private static ThreadLocal<String> windowHandleThreadLocal = new ThreadLocal<>();

    public static String getWindow() { return windowHandleThreadLocal.get(); }

    public static void setWindow(String window) { windowHandleThreadLocal.set(window); }

    public BasePage() {
        wait = Wait.getInstance();
    }

    public boolean isVisible(By by, int timeOutSeconds) {
        LOG.debug("Checking if element visible located: " + by);
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(by), timeOutSeconds) != null;
        } catch (NoSuchElementException | TimeoutException ex) {
            LOG.debug("Couldn't find element located by: " + by);
            return false;
        }
    }

    public boolean isVisible(By by) {
        return isVisible(by, 0);
    }

    protected boolean allAreVisible(By by, int timeOut) {
        LOG.debug("Checking if all elements are visible located: " + by);
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by), timeOut).size() > 0;
        } catch (StaleElementReferenceException ex) {
            LOG.error("Some element staled, re-trying");
            return allAreVisible(by, timeOut);
        } catch (TimeoutException ex) {
            return false;
        }
    }

    protected boolean allAreVisible(By by) {
        return allAreVisible(by, 0);
    }

    public String getCurrentUrl() {
        String url = getDriver().getCurrentUrl();
        LOG.debug("Url to be returned: " + url);
        return url;
    }

    public void navigateBack() {
        getDriver().navigate().back();
    }

    public BasePage refreshPage() {
        getDriver().navigate().refresh();
        return this;
    }

    protected WebElement getLastElement(By by) {
        return findWebElements(by).stream()
                .reduce((first, second) -> second)
                .orElseThrow(() -> new RuntimeException("Didn't find element with locator: " + by));
    }

    private Alert getAlert() {
        return Wait.getInstance().until(ExpectedConditions.alertIsPresent(), SHORT_WAIT_TIMEOUT_SECONDS);
    }

    public String getAlertText() {
        LOG.debug("Extracting alert message text");
        return getAlert().getText();
    }

    public void dismissAlert() {
        LOG.debug("Dismissing alert");
        getAlert().dismiss();
    }

    public void acceptAlert() {
        LOG.debug("Accepting alert");
        getAlert().accept();
    }

    public void backToDefaultContent() {
        getDriver().switchTo().defaultContent();
    }

    public void deleteAllCookies() {
        getDriver().manage().deleteAllCookies();
    }

    public WebElement getActiveWebElement() {
        return getDriver().switchTo().activeElement();
    }

    public String getTextFromWebElement(By by) {
        return findWebElement(by).getText();
    }

    public String getValueFromWebElement(By by) {
        return findWebElement(by).getCssValue("value");
    }

    public void typeText(By by, String text) {
        findWebElement(by).sendKeys(text);
    }

    public void clickOnElement(By by) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(by), 2);
            findWebElement(by).click();
        } catch (ElementNotVisibleException | TimeoutException ex) {
            LOG.info("Element is not clickable with locator: " + by);
        }
    }

    public void clickOnElementWithWait(By by, int time) {
        try {
            wait.waitFor(time);
            findWebElement(by).click();
        } catch (ElementNotVisibleException | TimeoutException ex) {
            LOG.info("Element is not clickable with locator: " + by);
        }
    }

    public void scrollUpToElement(WebElement element) {
        try {
            wait.waitFor(2_000);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView();", element);
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant scroll to element: " + element);
        }
    }
    public void scrollDownToElement(WebElement element) {
        try {
            wait.waitFor(2_000);
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("arguments[0].scrollIntoView(false)", element);
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant scroll to element: " + element);
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

    public void moveToElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element), 2);
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element).perform();
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant scroll to element: " + element);
        }
    }

    public void moveToElementAndClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element), 2);
            Actions actions = new Actions(getDriver());
            actions.moveToElement(element).click(element).perform();
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant scroll to element: " + element);
        }
    }

    public void selectByValue(By by, String text) {
        try {
            Select select = new Select(findWebElement(by));
            select.selectByValue(text);
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant select option: " + text);
        }
    }

    public void selectByIndex(By by, int index) {
        try {
            Select select = new Select(findWebElement(by));
            select.selectByIndex(index);
        } catch (ElementNotVisibleException | TimeoutException | StaleElementReferenceException ex) {
            LOG.info("Cant select option with index: " + index);
        }
    }

    public WebElement findWebElement(By by) {
        try {
            return getDriver().findElement(by);
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Element is not found with locator: " + by);
            return null;
        }
    }

    public List<WebElement> findWebElements(By by) {
        try {
            return getDriver().findElements(by);
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Elements are not found with locator: " + by);
            return null;
        }
    }

    public List<String> getAllTextsFromElement(By by) {
        try {
            return getDriver().findElements(by).stream()
                    .map(el -> el.getText().trim())
                    .collect(Collectors.toList());
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Elements are not found with locator: " + by);
            return null;
        }
    }

    public Set<String> getWindowHandles() {
        try {
            return getDriver().getWindowHandles();
        } catch (TimeoutException | ElementClickInterceptedException ex) {
            LOG.info("Windows are not opened");
            return null;
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

}
