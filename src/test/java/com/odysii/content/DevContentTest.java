package com.odysii.content;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.remote.ErrorCodes.TIMEOUT;


/**
 * Test Prefixes:
 * Dev user: dev_home_page
 * My apps: my_apps
 * Admin: admin_support_ticket
 */

public class DevContentTest extends TestBase {
    DevHomePage devHomePage;
    User user;
    ShowUp showUp;
    final String CATEGORYTEST = "Developer Content Tests";
    List<String> getAllOptions(By by) {
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(driver.findElement(by)).getOptions()) {
            String txt = option.getText();
            if (option.getAttribute("value") != "") options.add(option.getText());
        }
        return options;

    }


    @BeforeClass
    public void login(){
        user = new User(driver);
        devHomePage = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);

    }
    @Test
    public void _001_dev_home_page_valid_MyApps_texts(){
        logger = extent.startTest("_001_dev_home_page_valid_MyApps_texts").assignCategory(CATEGORYTEST);
        String expected = "MY APPS";
        String expectedHeader = "My Apps";
        devHomePage.getMyAppsPage(driver);
        String actualHeaderTxt = driver.findElement(By.className("h2")).getText();
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'My Apps')]")).getText();
        Assert.assertEquals(actualTxt,expected);
        Assert.assertEquals(actualHeaderTxt,expectedHeader);

    }
    @Test
    public void _002_my_apps_valid_app_status(){
        logger = extent.startTest("_002_my_apps_valid_app_status").assignCategory(CATEGORYTEST);
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        Assert.assertTrue((myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.SUBMITTED.getStatus().toLowerCase())
                || myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.PRESUBMITTED.getStatus().toLowerCase())
                || myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.CERTIFIED.getStatus().toLowerCase())
                || myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.REJECT.getStatus().toLowerCase())));

    }
    @Test
    public void _003_my_apps_add_new_app_valid_header() {
        logger = extent.startTest("_003_my_apps_add_new_app_valid_text").assignCategory(CATEGORYTEST);
        String expected = "ADD NEW APP";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        String actualTxt = driver.findElement(By.id("newAppButton")).getText();
        Assert.assertEquals(actualTxt.trim(), expected.trim());
        myApps.clickAddNewAppBtn();
        String expectedWizHeader = "Add New App";
        WebElement addNewappWiz = driver.findElement(By.cssSelector(".col.mx-0.mx-sm-2.mx-lg-3.mt-1.pr-0"));
        isElementPresent(addNewappWiz);
        String actualWizHeader = addNewappWiz.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(actualWizHeader, expectedWizHeader);
        String expectedAppDtls = "App Details";
        WebElement wizardNewApp = driver.findElement(By.cssSelector(".col.mx-0.mx-sm-2.mx-lg-3.mt-1.pr-0"));
        String actualAppDtls = wizardNewApp.findElement(By.tagName("a")).getText();
        Assert.assertEquals(actualAppDtls, expectedAppDtls);
    }
    @Test
    public void _004_my_apps_add_new_app_valid_text_appName() {
        logger = extent.startTest("_004_my_apps_add_new_app_valid_text_appName").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedNameLbl = "Name";
        String actualNameLbl = newAppForm.findElement(By.tagName("label")).getText();
        Assert.assertEquals(actualNameLbl, expectedNameLbl);
        String expectedNamePlhdr = "My First App";
        String actualNamePlhdr = newAppForm.findElement(By.id("appName")).getAttribute("placeholder");
        Assert.assertEquals(actualNamePlhdr, expectedNamePlhdr);
    }

    @Test
    public void _005_my_apps_add_new_app_valid_text_version() {
        logger = extent.startTest("_005_my_apps_add_new_app_valid_text_version").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedVerLbl = "Version";
        String actualVerLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Version')]")).getText();
        Assert.assertEquals(actualVerLbl, expectedVerLbl);
        String expectedVerPlhdr = "1.0.2";
        String actualVerPlhdr = driver.findElement(By.id("appVersion")).getAttribute("placeholder");
        Assert.assertEquals(actualVerPlhdr, expectedVerPlhdr);
    }

    @Test
    public void _006_my_apps_add_new_app_valid_text_SubTitle() {
        logger = extent.startTest("_006_my_apps_add_new_app_valid_text_SubTitle").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedSubLbl = "Subtitle";
        String actualSubLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Subtitle')]")).getText();
        Assert.assertEquals(actualSubLbl, expectedSubLbl);
        String expectedSubPlhdr = "Enter the app subtitle, if applicable";
        String actualSubPlhdr = driver.findElement(By.id("appSubtitle")).getAttribute("placeholder");
        Assert.assertEquals(actualSubPlhdr, expectedSubPlhdr);
    }
    @Test
    public void _007_my_apps_add_new_app_valid_text_language() {
        logger = extent.startTest("_007_my_apps_add_new_app_valid_text_language").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedLangLbl = "Language";
        String actualLangLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Language')]")).getText();
        Assert.assertEquals(actualLangLbl, expectedLangLbl);
        String expectedDrpdwnLangTxt = "Select languages";
        String actualDrpdwnLangTxt = driver.findElement(By.id("appLanguages")).getText();
        Assert.assertEquals(actualDrpdwnLangTxt, expectedDrpdwnLangTxt);
    }
    //List listLang =  getAllOptions(By.id("appLanguages"));
    //WebElement res = driver.findElement(By.id("appLanguages"));
    //System.out.println(listLang);


    @Test
    public void _008_my_apps_add_new_app_valid_text_category() {
        logger = extent.startTest("_008_my_apps_add_new_app_valid_text_category").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedCatgLbl = "Category";
        String actualCatLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Category')]")).getText();
        Assert.assertEquals(actualCatLbl, expectedCatgLbl);
        String expectedDrpdwnCatTxt = "Select categories";
        String actualDrpdwncatTxt = driver.findElement(By.id("AppCategories")).getText();
        Assert.assertEquals(actualDrpdwncatTxt, expectedDrpdwnCatTxt);
    }
    @Test
    public void _009_my_apps_add_new_app_valid_text_availability() {
        logger = extent.startTest("_009_my_apps_add_new_app_valid_text_availability").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedAvlLbl = "Availability";
        String actualAvlLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Availability')]")).getText();
        Assert.assertEquals(actualAvlLbl, expectedAvlLbl);
        String expectedDrpdwnAvlTxt = "    Please choose availability\n" +
                "    Private\n" +
                "Public\n" +
                "  ";
        String actualDrpdwnAvlTxt = driver.findElement(By.id("appAvailability")).getText();
        Assert.assertEquals(actualDrpdwnAvlTxt, expectedDrpdwnAvlTxt);
    }

    @Test
    public void _010_my_apps_add_new_app_valid_text_retailers() {
        logger = extent.startTest("_010_my_apps_add_new_app_valid_text_retailers").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedRtlLbl = "Retailers";
        String actualRtlLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Retailers')]")).getText();
        Assert.assertEquals(actualRtlLbl, expectedRtlLbl);
        String expectedDrpdwnRtlTxt = "Select retailers";
        String actualDrpdwnRtlTxt = driver.findElement(By.id("appRetailers")).getText();
        Assert.assertEquals(actualDrpdwnRtlTxt, expectedDrpdwnRtlTxt);
    }

    @Test
    public void _011_my_apps_add_new_app_valid_text_pricing() {
        logger = extent.startTest("_011_my_apps_add_new_app_valid_text_pricing").assignCategory(CATEGORYTEST);
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedPrcLbl = "Pricing";
        String actualPrcLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Pricing')]")).getText();
        Assert.assertEquals(actualPrcLbl, expectedPrcLbl);
        String expectedPrcTxt = "Price";
        String actualPrcTxt = driver.findElement(By.id("appPrice")).getAttribute("placeholder");
        Assert.assertEquals(actualPrcTxt, expectedPrcTxt);
        String expectedTypLbl = "Pricing Type";
        String actualTypLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Pricing Type')]")).getText();
        Assert.assertEquals(actualTypLbl, expectedTypLbl);
        String expectedTypTxt = "      Please choose pricing type\n" +
                "      Per dispenser per month\n" +
                "Per dispenser per year\n" +
                "Per site per year\n" +
                "One-time flat fee\n" +
                "    ";
        String actualTypTxt = driver.findElement(By.id("appPriceType")).getText();
        Assert.assertEquals(actualTypTxt, expectedTypTxt);
        WebElement cancelBtn = driver.findElement(By.id("cancelButton"));
        cancelBtn.click();


    }

    @Test
    public void _012_my_apps_new_version_valid_text(){
        logger = extent.startTest("_012_my_apps_new_version_valid_text").assignCategory(CATEGORYTEST);
        String expected = "NEW VERSION";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.id("editAppNewVersion")).getText();
        Assert.assertEquals(expected.trim(),actualTxt.trim());

    }
    @Test
    public void _013_my_apps_what_new_in_this_version_valid_text(){
        logger = extent.startTest("_013_my_apps_what_new_in_this_version_valid_text").assignCategory(CATEGORYTEST);
        String expected = "What's new in this version?";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.className("mb-2")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _014_my_apps_version_valid_texts(){
        logger = extent.startTest("_014_my_apps_version_valid_texts").assignCategory(CATEGORYTEST);
        String expected = "Versions";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.xpath("//*[contains(text(), 'Versions')]")).getText();
        Assert.assertEquals(expected,actualTxt);
        String expectedHeader = "App Versions";
        String actualHeader = driver.findElement(By.cssSelector(".col.row.mx-0.mx-sm-2.mx-lg-3.mt-1")).getText();
        Assert.assertEquals(actualHeader,expectedHeader);

    }
    @Test
    public void _015_my_apps_back_to_my_apps_valid_text() {
        logger = extent.startTest("_015_my_apps_back_to_my_apps_valid_text").assignCategory(CATEGORYTEST);
        String expected = "Back to My Apps";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        showUp = myApps.showUp(0);
        String actualTxt = driver.findElement(By.className("nav-text")).getText();
        showUp.backToMyApps();
        Assert.assertEquals(expected, actualTxt);

    }

    @Test
    public void _016_dev_home_page_valid_Dashboard_header(){
        logger = extent.startTest("_016_dev_home_page_valid_Dashboard_header").assignCategory(CATEGORYTEST);
        String expected = "DASHBOARD";
        devHomePage.getDashboardPage(driver);
        String actualTxt = driver.findElement(By.xpath("//*[contains(text(), 'Dashboard')]")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _017_dev_home_page_valid_TransactionHistory_header(){
        logger = extent.startTest("_017_dev_home_page_valid_TransactionHistory_header").assignCategory(CATEGORYTEST);
        String expected = "TRANSACTION HISTORY";
        devHomePage.getTrasactionHistoryPage(driver);
        String actualTxt = driver.findElement(By.xpath("//*[contains(text(), 'Transaction History')]")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _018_home_page_valid_RevenueReport_header(){
        logger = extent.startTest("_018_home_page_valid_RevenueReport_header").assignCategory(CATEGORYTEST);
        String expected = "REVENUE REPORT";
        devHomePage.getRevenueReportPage(driver);
        String actualTxt = driver.findElement(By.xpath("//*[contains(text(), 'Revenue Report')]")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _019_home_page_valid_SupportTickets_header(){
        logger = extent.startTest("_019_home_page_valid_SupportTickets_header").assignCategory(CATEGORYTEST);
        String expected = "SUPPORT TICKETS";
        devHomePage.getSupportTicketstPage(driver);
        String actualTxt = driver.findElement(By.xpath("//*[contains(text(), 'Support Tickets')]")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _020_home_page_valid_PublicProfile_header(){
        logger = extent.startTest("_020_home_page_valid_PublicProfile_header").assignCategory(CATEGORYTEST);
        String expected = "PUBLIC PROFILE";
        devHomePage.getPublicProfilePage(driver);
        String actualTxt = driver.findElement(By.xpath("//*[contains(text(), 'Public Profile')]")).getText();
        Assert.assertEquals(actualTxt,expected);
    }

}