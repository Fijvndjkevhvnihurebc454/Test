package tests;

import framework.base.BaseTest;
import framework.constants.Departments;
import framework.constants.Locations;
import org.testng.annotations.Test;
import pages.CareersPage;
import pages.HomePage;
import pages.LeverApplicationFormPage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class BaseChecksTests extends BaseTest {
    CareersPage careersPage;

    @Test
    public void checkInsiderHomePageIsOpenedTest() {
        HomePage homePage = new HomePage();
        homePage.clickLogo();
        assertThat(homePage.isWhyInsiderVisible()).as("Why Insider is not visible").isTrue();
        assertThat(homePage.isPlatformVisible()).as("Platform is not visible").isTrue();
        assertThat(homePage.isSolutionsVisible()).as("Solutions is not visible").isTrue();
        assertThat(homePage.isResourcesVisible()).as("Resources is not visible").isTrue();
        assertThat(homePage.isCompanyVisible()).as("Company is not visible").isTrue();
    }

    @Test
    public void checkCareersPageFiltersTest() {
        HomePage homePage = new HomePage();
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        assertThat(careersPage.isFindJobUpperButtonVisible()).as("Find Job upper button is not visible").isTrue();
        careersPage.clickFindJob();
        assertThat(careersPage.isSelectFilterByLocationVisible()).as("Filter by Location is not visible").isTrue();
        assertThat(careersPage.isSelectFilterByDepartmentVisible()).as("Filter by Department is not visible").isTrue();
        careersPage.clickSelectFilterByLocation();
        assertThat(careersPage.getFilterByOptions().size() > 0).as("").isTrue();
        assertThat(careersPage.getFilterByOptions().containsAll(List.of("All", "Istanbul, Turkey", "Sydney, Australia", "London, United Kingdom",
                "Seoul, South Korea", "United States", "Berlin, Germany", "Turkey", "Warsaw, Poland")))
                .as("Not all required locations are present").isTrue();
        careersPage.clickSelectFilterByDepartmentLabel();
        careersPage.clickSelectFilterByDepartment();
        assertThat(careersPage.getFilterByOptions().size() > 0).as("").isTrue();
        assertThat(careersPage.getFilterByOptions().containsAll(List.of("All", "Business Development", "Business Intelligence", "Finance & Business Support",
                "Quality Assurance", "Sales", "Software Development", "Mobile Development", "Product Management")))
                .as("Not all required departments are present").isTrue();
      }

    @Test
    public void checkPositionTitlesTest() {
        HomePage homePage = new HomePage();
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.clickFindJob();
        careersPage.selectDepartment(Departments.QUALITY_ASSURANCE.getDepartment());
        careersPage.selectLocation(Locations.INSTANBUL_TURKEY.getLocation());
        assertThat(careersPage.validateLocationFilter(Locations.INSTANBUL_TURKEY.getLocation()))
                .as("Location filter applied not correctly").isTrue();
    }

    @Test
    public void checkNewRoleButtonRedirectionTest() {
        HomePage homePage = new HomePage();
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.clickFindJob();
        careersPage.selectDepartment(Departments.QUALITY_ASSURANCE.getDepartment());
        careersPage.selectLocation(Locations.INSTANBUL_TURKEY.getLocation());
        String baseWindow = careersPage.getWindowHandle();
        LeverApplicationFormPage leverApplicationFormPage = careersPage.clickFirstViewRoleButton();
        careersPage.switchToNewPage(baseWindow);
        assertThat(leverApplicationFormPage.isHeaderLogoVisible()).as("Logo on Application Form is not visible").isTrue();
        assertThat(leverApplicationFormPage.isApplyForJobUpperButtonVisible()).as("Apply For This Job on Application Form is not visible").isTrue();
    }
}
