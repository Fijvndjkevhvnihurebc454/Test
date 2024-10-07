package tests;

import framework.base.BaseTest;
import framework.constants.Departments;
import framework.constants.Locations;
import org.testng.annotations.Test;
import pages.*;

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
        careersPage.selectLocation(Locations.ISTANBUL_TURKEY.getLocation());
        assertThat(careersPage.validateLocationFilter(Locations.ISTANBUL_TURKEY.getLocation()))
                .as("Location filter applied not correctly").isTrue();
        careersPage.clickRightArrow();
        assertThat(careersPage.validateLocationFilter(Locations.ISTANBUL_TURKEY.getLocation()))
                .as("Location filter applied not correctly").isTrue();
        careersPage.clickRightArrow();
        assertThat(careersPage.validateLocationFilter(Locations.ISTANBUL_TURKEY.getLocation()))
                .as("Location filter applied not correctly").isTrue();
    }

    @Test
    public void checkCareerLocationsTeamsViewTest() {
        HomePage homePage = new HomePage();
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.scrollToSeeAllTeams();
        careersPage.clickSeeAllTeams();
        assertThat(careersPage.isTeamByTitleVisible("Customer Success")).as("Customer Success is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Customer Success")).as("Customer Success is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Sales")).as("Sales is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Sales")).as("Sales is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Product & Engineering")).as("Product & Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Product & Engineering")).as("Product & Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Finance & Business Support")).as("Finance & Business Support is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Finance & Business Support")).as("Finance & Business Support is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Marketing")).as("Marketing is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Marketing")).as("Marketing is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("CEO’s Executive Office")).as("CEO’s Executive Office is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("CEO’s Executive Office")).as("CEO’s Executive Office is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Purchasing & Operations")).as("Purchasing & Operations is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Purchasing & Operations")).as("Purchasing & Operations is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("People and Culture")).as("People and Culture is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("People and Culture")).as("People and Culture is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Business Intelligence")).as("Business Intelligence is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Business Intelligence")).as("Business Intelligence is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Security Engineering")).as("Security Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Security Engineering")).as("Security Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Partnership")).as("Partnership is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Partnership")).as("Partnership is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Quality Assurance")).as("Quality Assurance is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Quality Assurance")).as("Quality Assurance is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Mobile Business Unit")).as("Mobile Business Unit is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Mobile Business Unit")).as("Mobile Business Unit is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Partner Support Development")).as("Partner Support Development is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Partner Support Development")).as("Partner Support Development is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Product Design")).as("Product Design is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Product Design")).as("Product Design is not visible").isTrue();
        careersPage.scrollToLocationSlide();
        assertThat(careersPage.isOurLocationsBlockVisible()).as("Locations Block is not visible").isTrue();
        assertThat(careersPage.isOurLocationsLeftArrowVisible()).as("Locations left arrow is not visible").isTrue();
        assertThat(careersPage.isOurLocationsRightArrowVisible()).as("Locations right arrow is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("New York")).as("New York is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("Sao Paulo")).as("Sao Paulo is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("London")).as("London is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("Paris")).as("Paris is not visible").isTrue();
        careersPage.scrollToLifeInsiderLabel();
        assertThat(careersPage.isSwipeWrapperVisible()).as("Insider Swiper is not visible").isTrue();
    }

    @Test
    public void checkNewRoleButtonRedirectionTest() {
        HomePage homePage = new HomePage();
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.scrollToSeeAllTeams();
        careersPage.clickSeeAllTeams();
        assertThat(careersPage.isTeamByTitleVisible("Customer Success")).as("Customer Success is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Customer Success")).as("Customer Success is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Sales")).as("Sales is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Sales")).as("Sales is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Product & Engineering")).as("Product & Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Product & Engineering")).as("Product & Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Finance & Business Support")).as("Finance & Business Support is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Finance & Business Support")).as("Finance & Business Support is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Marketing")).as("Marketing is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Marketing")).as("Marketing is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("CEO’s Executive Office")).as("CEO’s Executive Office is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("CEO’s Executive Office")).as("CEO’s Executive Office is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Purchasing & Operations")).as("Purchasing & Operations is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Purchasing & Operations")).as("Purchasing & Operations is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("People and Culture")).as("People and Culture is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("People and Culture")).as("People and Culture is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Business Intelligence")).as("Business Intelligence is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Business Intelligence")).as("Business Intelligence is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Security Engineering")).as("Security Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Security Engineering")).as("Security Engineering is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Partnership")).as("Partnership is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Partnership")).as("Partnership is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Quality Assurance")).as("Quality Assurance is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Quality Assurance")).as("Quality Assurance is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Mobile Business Unit")).as("Mobile Business Unit is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Mobile Business Unit")).as("Mobile Business Unit is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Partner Support Development")).as("Partner Support Development is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Partner Support Development")).as("Partner Support Development is not visible").isTrue();
        assertThat(careersPage.isTeamByTitleVisible("Product Design")).as("Product Design is not visible").isTrue();
        assertThat(careersPage.isTeamByTitlePositionsVisible("Product Design")).as("Product Design is not visible").isTrue();
        careersPage.scrollToLocationSlide();
        assertThat(careersPage.isOurLocationsBlockVisible()).as("Locations Block is not visible").isTrue();
        assertThat(careersPage.isOurLocationsLeftArrowVisible()).as("Locations left arrow is not visible").isTrue();
        assertThat(careersPage.isOurLocationsRightArrowVisible()).as("Locations right arrow is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("New York")).as("New York is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("Sao Paulo")).as("Sao Paulo is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("London")).as("London is not visible").isTrue();
        assertThat(careersPage.isLocationsCityVisible("Paris")).as("Paris is not visible").isTrue();
        careersPage.scrollToLifeInsiderLabel();
        assertThat(careersPage.isSwipeWrapperVisible()).as("Insider Swiper is not visible").isTrue();
    }

    @Test
    public void checkTeamsLifeAtInsiderBlocksAreOpenedTest() {
        HomePage homePage = new HomePage();
        CustomerSuccessPage customerSuccessPage;
        QualityAssurancePage qualityAssurancePage;
        MarketingPage marketingPage;
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.scrollToSeeAllTeams();
        careersPage.clickSeeAllTeams();
        customerSuccessPage = careersPage.clickTeamByTitleLabel("Customer Success");
        assertThat(customerSuccessPage.isCustomerSuccessVisible()).as("Customer Success page is not opened").isTrue();
        customerSuccessPage.navigateTo("https://useinsider.com/careers/");
        homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.scrollToSeeAllTeams();
        careersPage.clickSeeAllTeams();
        marketingPage = careersPage.clickTeamByTitleLabel("Marketing");
        assertThat(marketingPage.isAllMarketingJobsVisible()).as("Marketing page is not opened").isTrue();
        customerSuccessPage.navigateTo("https://useinsider.com/careers/");
        homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.scrollToSeeAllTeams();
        careersPage.clickSeeAllTeams();
        qualityAssurancePage = careersPage.clickTeamByTitleLabel("Quality Assurance");
        assertThat(qualityAssurancePage.isQualityAssuranceVisible()).as("Quality Assurance page is not opened").isTrue();
    }

    @Test
    public void checkRedirectionToNewJobsPageTest() {
        HomePage homePage = new HomePage();
        QualityAssurancePage qualityAssurancePage = new QualityAssurancePage();
        careersPage = homePage.clickCompanyDropDown()
                .clickCareersTitle();
        careersPage.navigateTo("https://useinsider.com/careers/quality-assurance/");
        qualityAssurancePage.clickSeeAllQualityAssuranceJobs();
        careersPage.selectDepartment(Departments.QUALITY_ASSURANCE.getDepartment());
        careersPage.selectLocation(Locations.ISTANBUL_TURKEY.getLocation());
        String baseWindow = careersPage.getWindowHandle();
        LeverApplicationFormPage leverApplicationFormPage = careersPage.clickFirstViewRoleButton();
        careersPage.switchToNewPage(baseWindow);
        assertThat(leverApplicationFormPage.isHeaderLogoVisible()).as("Logo on Application Form is not visible").isTrue();
        assertThat(leverApplicationFormPage.isApplyForJobUpperButtonVisible()).as("Apply For This Job on Application Form is not visible").isTrue();    }
}
