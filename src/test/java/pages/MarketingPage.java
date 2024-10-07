package pages;

import org.openqa.selenium.By;

public class MarketingPage extends CareersPage {
    By seeAllMarketingJobs = By.cssSelector("#page-head .button-group a");

    public boolean isAllMarketingJobsVisible() { return isVisible(seeAllMarketingJobs); }
}
