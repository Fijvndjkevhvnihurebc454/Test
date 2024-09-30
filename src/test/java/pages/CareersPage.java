package pages;

import framework.base.BasePage;
import framework.constants.HomeDropdowns;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CareersPage extends BasePage {
    By findJobUpperButton = By.xpath("//section[contains(@id, 'page-head')]//a[text()='Find your dream job']");
    String homeDropdown = "//li[contains(@class,'nav-item')]//a[contains(text(),'%s')]";
    By selectFilterByLocation = By.cssSelector("#select2-filter-by-location-container span");
    String selectFilterLocationOption = "//li[contains(@id, 'filter-by-location-result') and text()='%s']";
    String selectFilterDepartmentOption = "//li[contains(@id, 'filter-by-department-result') and text()='%s']";
    By selectFilterByDepartmentLabel = By.cssSelector("label[for=filter-by-department]");
    By selectFilterByDepartment = By.cssSelector("#select2-filter-by-department-container span");
    By filterByOptions = By.cssSelector(".select2-results__option");
    By positionLocations = By.xpath("//div[contains(@class, 'position-list-item')]//div[contains(@class, 'position-location')]");
    By viewRoleButton = By.xpath("//a[text()='View Role']");
    By positions = By.cssSelector(".position-list-item");
    By positionsTitle = By.cssSelector(".position-list-item .position-title");

    public CareersPage() {
        waitForCareersPageLoaded();
    }

    public CareersPage waitForCareersPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(findJobUpperButton), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(homeDropdown,
                HomeDropdowns.WHY_INSIDER.getTitle()))), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(homeDropdown,
                HomeDropdowns.PLATFORM.getTitle()))), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(homeDropdown,
                HomeDropdowns.SOLUTIONS.getTitle()))), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(homeDropdown,
                HomeDropdowns.RESOURCES.getTitle()))), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(homeDropdown,
                HomeDropdowns.COMPANY.getTitle()))), 10);
        return this;
    }

    public boolean isFindJobUpperButtonVisible() {
        return isVisible(findJobUpperButton, 2);
    }

    public CareersPage clickFindJob() {
        clickOnElement(findJobUpperButton);
        return this;
    }

    public boolean isSelectFilterByLocationVisible() {
        return isVisible(selectFilterByLocation, 10);
    }

    public CareersPage clickSelectFilterByLocation() {
        clickOnElementWithWait(selectFilterByLocation, 5_000);
        wait.until(ExpectedConditions.numberOfElementsToBe(filterByOptions, 22));
        return this;
    }

    public CareersPage selectLocation(String location) {
        clickOnElementWithWait(selectFilterByLocation, 5_000);
        scrollDownToElement(findWebElement(By.xpath(String.format(selectFilterLocationOption, location))));
        clickOnElement(By.xpath(String.format(selectFilterLocationOption, location)));
        return this;
    }

    public CareersPage clickSelectFilterByDepartmentLabel() {
        clickOnElement(selectFilterByDepartmentLabel);
        return this;
    }

    public CareersPage selectDepartment(String department) {
        clickOnElementWithWait(selectFilterByDepartment, 5_000);
        scrollDownToElement(findWebElement(By.xpath(String.format(selectFilterDepartmentOption, department))));
        clickOnElement(By.xpath(String.format(selectFilterDepartmentOption, department)));
        return this;
    }

    public boolean isSelectFilterByDepartmentVisible() {
        return isVisible(selectFilterByDepartment, 10);
    }

    public CareersPage clickSelectFilterByDepartment() {
        clickOnElementWithWait(selectFilterByDepartment, 5_000);
        wait.until(ExpectedConditions.numberOfElementsToBe(filterByOptions, 17));
        return this;
    }

    public List<String> getFilterByOptions() {
        return getAllTextsFromElement(filterByOptions);
    }

    public List<String> getLocations() {
        return getAllTextsFromElement(positionLocations);
    }

    public boolean validateLocationFilter(String location) {
        scrollDown();
        return getLocations().stream()
                .allMatch(filter -> filter.equals(location));
    }

    public void switchToNewPage(String baseWindow) {
        String newWindow = getWindowHandles().stream()
                .filter(window -> !window.equals(baseWindow))
                .findFirst()
                .get();
        switchToWindow(newWindow);
    }

    public LeverApplicationFormPage clickFirstViewRoleButton() {
        scrollDownToElement(findWebElement(positionLocations));
        scrollDown();
        moveToElementAndClick(findWebElement(positionLocations));
        clickOnElement(viewRoleButton);
        return new LeverApplicationFormPage();
    }




}
