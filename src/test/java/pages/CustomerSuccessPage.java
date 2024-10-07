package pages;

import org.openqa.selenium.By;

public class CustomerSuccessPage extends CareersPage {
    By seeAllCustomerSuccessJobs = By.cssSelector("#page-head .button-group a");

    public boolean isCustomerSuccessVisible() { return isVisible(seeAllCustomerSuccessJobs); }
}
