package pages;

import org.openqa.selenium.By;

public class QualityAssurancePage extends CareersPage {
    By seeAllQualityAssuranceJobs = By.cssSelector("#page-head .button-group a");

    public QualityAssurancePage clickSeeAllQualityAssuranceJobs() {
        clickOnElement(seeAllQualityAssuranceJobs);
        return this;
    }

    public boolean isQualityAssuranceVisible() {
        return isVisible(seeAllQualityAssuranceJobs, 2);
    }

}
