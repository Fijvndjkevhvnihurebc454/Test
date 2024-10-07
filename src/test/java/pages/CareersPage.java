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
    By ourLocationsBlock = By.cssSelector("#career-our-location .category-title-media");
    By locationsSlider = By.cssSelector("#location-slider");
    By locationsLeftArrow = By.cssSelector("#career-our-location .icon-arrow-left");
    By locationsRightArrow = By.cssSelector("#career-our-location .icon-arrow-right");
    String locationCity = "//div[@class='location-info']//p[text()='%s']";
    By seeAllTeamsButton = By.cssSelector("#career-find-our-calling a.loadmore");
    String teamByTitle = "//div[contains(@class, 'job-item')]//div[contains(@class,'job-title')]//h3[text()='%s']";
    String teamByTitlePositions = "//div[contains(@class, 'job-item')][.//h3[text()='%s']]//div[contains(@class, 'job-open-position')]";
    By lifeInsiderLabel = By.xpath("//h2[text()='Life at Insider']");
    By swipeWrapper = By.cssSelector(".elementor-swiper .swiper-wrapper");
    By rightArrow = By.cssSelector(".pagination-desktop .jobs-pagination .icon-arrow-right");

    public CareersPage() {
        waitForCareersPageLoaded();
    }

    public CareersPage waitForCareersPageLoaded() {
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
        scrollDown();
        clickOnElementWithWait(selectFilterByLocation, 5_000);
        scrollDownToElement(findWebElement(By.xpath(String.format(selectFilterLocationOption, location))));
        clickOnElement(By.xpath(String.format(selectFilterLocationOption, location)));
        return this;
    }

    public CareersPage scrollToLocationSlide() {
        scrollDownToElement(findWebElement(locationsSlider));
        return this;
    }

    public CareersPage scrollToSeeAllTeams() {
        scrollDownToElement(findWebElement(seeAllTeamsButton));
        return this;
    }

    public CareersPage scrollToLifeInsiderLabel() {
        scrollDownToElement(findWebElement(lifeInsiderLabel));
        return this;
    }

    public boolean isSwipeWrapperVisible() { return isVisible(swipeWrapper); }

    public CareersPage clickSeeAllTeams() {
        clickOnElementWithWait(seeAllTeamsButton, 2_000);
        return this;
    }

    public CareersPage clickRightArrow() {
        scrollDownToElement(findWebElement(rightArrow));
        clickOnElementWithWait(rightArrow, 2_000);
        return this;
    }

    public boolean isOurLocationsBlockVisible() { return isVisible(ourLocationsBlock); }

    public boolean isOurLocationsLeftArrowVisible() { return isVisible(locationsLeftArrow); }

    public boolean isOurLocationsRightArrowVisible() { return isVisible(locationsRightArrow); }

    public boolean isLocationsCityVisible(String city) { return isVisible(By.xpath(String.format(locationCity, city))); }

    public CareersPage clickSelectFilterByDepartmentLabel() {
        clickOnElement(selectFilterByDepartmentLabel);
        return this;
    }

    public boolean isTeamByTitleVisible(String title) { return isVisible(By.xpath(String.format(teamByTitle, title)), 2); }

    public boolean isTeamByTitlePositionsVisible(String title) { return isVisible(By.xpath(String.format(teamByTitlePositions, title))); }

    public CareersPage selectDepartment(String department) {
        scrollDown();
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
        waitForCareersPageLoaded();
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

    public <T extends CareersPage> T clickTeamByTitleLabel(String title) {
        wait.waitFor(3_000);
        scrollDownToElement(findWebElement(By.xpath(String.format(teamByTitle, title))));
        clickOnElementWithWait(By.xpath(String.format(teamByTitle, title)), 2_000);
        switch (title) {
            case "Customer Success":
                return (T) new CustomerSuccessPage();
            case "Marketing":
                return (T) new MarketingPage();
            case "Quality Assurance":
                return (T) new QualityAssurancePage();
            default:
                return (T) new CareersPage();
        }
    }

}
