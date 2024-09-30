package pages;

import framework.base.BasePage;
import framework.constants.CompanySubMenus;
import framework.constants.HomeDropdowns;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    By loginButton = By.xpath("//a[text()='Login']");
    By logo = By.cssSelector("img[alt='insider_logo']");
    By getDemoButton = By.xpath("//li[@class='nav-item']//a[text()='Get a Demo']");
    String homeDropdown = "//li[contains(@class,'nav-item')]//a[contains(text(),'%s')]";
    String companySubDropdown = "//a[contains(@class,'dropdown-sub') and text()='%s']";

    public HomePage() {
        waitForLoginPageLoaded();
    }

    public HomePage waitForLoginPageLoaded() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton), 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getDemoButton), 10);
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

    public boolean isTakeTourVisible() {
        return isVisible(By.xpath(String.format(homeDropdown,
                HomeDropdowns.TAKE_TOUR.getTitle())), 5);
    }

    public boolean isWhyInsiderVisible() {
        return isVisible(By.xpath(String.format(homeDropdown,
                HomeDropdowns.WHY_INSIDER.getTitle())), 5);
    }

    public boolean isPlatformVisible() {
        return isVisible(By.xpath(String.format(homeDropdown,
                HomeDropdowns.PLATFORM.getTitle())), 5);
    }

    public boolean isSolutionsVisible() {
        return isVisible(By.xpath(String.format(homeDropdown,
                HomeDropdowns.SOLUTIONS.getTitle())), 5);
    }

    public boolean isResourcesVisible() {
        return isVisible(By.xpath(String.format(homeDropdown,
                HomeDropdowns.RESOURCES.getTitle())), 5);
    }

    public boolean isCompanyVisible() {
        return isVisible(By.xpath(String.format(homeDropdown,
                HomeDropdowns.COMPANY.getTitle())), 5);
    }

    public HomePage clickCompanyDropDown() {
        clickOnElement(By.xpath(String.format(homeDropdown, HomeDropdowns.COMPANY.getTitle())));
        return this;
    }

    public CareersPage clickCareersTitle() {
        clickOnElement(By.xpath(String.format(companySubDropdown, CompanySubMenus.CAREERS.getTitle())));
        return new CareersPage();
    }

    public HomePage clickLogo() {
        clickOnElement(logo);
        return this;
    }

}
