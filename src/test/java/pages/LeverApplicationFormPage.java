package pages;

import framework.base.BasePage;
import org.openqa.selenium.By;

public class LeverApplicationFormPage extends BasePage {
    By applyForJobUpperButton = By.cssSelector(".posting-header a.template-btn-submit");
    By headerLogo = By.cssSelector(".main-header-content .main-header-logo");

    public boolean isApplyForJobUpperButtonVisible() {
        return isVisible(applyForJobUpperButton, 2);
    }

    public boolean isHeaderLogoVisible() {
        return isVisible(headerLogo, 2);
    }
}
