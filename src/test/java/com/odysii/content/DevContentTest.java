package com.odysii.content;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Test Prefixes:
 * Dev user: dev_home_page
 * My apps: my_apps
 * Admin: admin_support_ticket
 */

public class DevContentTest extends TestBase {
    ShowUp showUp;
    MyApps myApps;
    AppDetails appDetails;
    UploadCode uploadCode;
    Marketing marketing ;
    Capabilities cap;
    final String zipFile = "TH.zip";
    List<String> getAllOptions(By by) {
        List<String> options = new ArrayList<String>();
        for (WebElement option : new Select(driver.findElement(by)).getOptions()) {
            String txt = option.getText();
            if (option.getAttribute("content/value") != "") options.add(option.getText());
        }
        return options;

    }


    @BeforeClass
    public void prepare(){
        if (!isPrepared){
            prepareTest("app_details_DevContent_PreSubmitted.properties",ApplicationStatus.PRESUBMITTED);
//            prepareTest("app_details_DevContent_Submitted.properties",ApplicationStatus.SUBMITTED);
//            prepareTest("app_details_DevContent_Certified.properties",ApplicationStatus.CERTIFIED);
//            prepareTest("app_details_DevContent_Rejected.properties",ApplicationStatus.REJECT);
//            prepareTest("app_details_DevContent_Live.properties",ApplicationStatus.LIVE);
            isPrepared = true;
        }else {
            user = new User(driver);
            devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        }
        category = "Dev Content";

    }

    @Test
    public void _001_dev_home_page_valid_MyApps_texts(){
        String expected = "MY APPS";
        String expectedHeader = "My Apps";
        devUser.getMyAppsPage(driver);
        WebElement myAppsPage = driver.findElement(By.cssSelector(".col.row.mx-0.mx-sm-2.mx-lg-3.mt-1"));
        String actualHeaderTxt = myAppsPage.findElement(By.className("h2")).getText().trim();
        String actualTxt = driver.findElement(By.id("navItem5")).getText().trim().toUpperCase();
        Assert.assertEquals(actualTxt,expected);
        Assert.assertEquals(actualHeaderTxt,expectedHeader);

    }

   @Test
    public void _002_check_app_status_presubmitted(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        String expectedAppName = "DEVCONTENT_APP_PRESUBMITTED";
        String actualAppName = driver.findElement(By.xpath("//*[contains(text(), 'DevContent_App_PreSubmitted')]")).getText().toUpperCase().split(":")[0];
        Assert.assertEquals(actualAppName,expectedAppName);
        String expectedAppStatus = "PRESUBMITTED";
        String actualAppStatus = driver.findElements(By.className("cx-status")).get(0).getText().toUpperCase().trim();
        Assert.assertEquals(actualAppStatus,expectedAppStatus);

    }

    //@Test
    public void _003_check_app_status_submitted(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        String expectedAppName = "DEVCONTENT_APP_SUBMITTED";
        String actualAppName = driver.findElement(By.xpath("//*[contains(text(), 'DevContent_App_Submitted')]")).getText().toUpperCase().split(":")[0];
        Assert.assertEquals(actualAppName, expectedAppName);
        String expectedAppStatus = "SUBMITTED";
        String actualAppStatus = driver.findElements(By.className("cx-status")).get(1).getText().toUpperCase().trim();
        Assert.assertEquals(actualAppStatus, expectedAppStatus);
    }

    //@Test
    public void _004_check_app_status_certified(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        String expectedAppName = "DEVCONTENT_APP_CERTIFIED";
        String actualAppName = driver.findElement(By.xpath("//*[contains(text(), 'DevContent_App_Certified')]")).getText().toUpperCase().split(":")[0];
        Assert.assertEquals(actualAppName, expectedAppName);
        String expectedAppStatus = "CERTIFIED";
        String actualAppStatus = driver.findElements(By.className("cx-status")).get(2).getText().toUpperCase().trim();
        Assert.assertEquals(actualAppStatus, expectedAppStatus);
    }


    //@Test
    public void _005_check_app_status_rejected(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        String expectedAppName = "DEVCONTENT_APP_REJECTED";
        String actualAppName = driver.findElement(By.xpath("//*[contains(text(), 'DevContent_App_Rejected')]")).getText().toUpperCase().split(":")[0];
        Assert.assertEquals(actualAppName, expectedAppName);
        String expectedAppStatus = "REJECTED";
        String actualAppStatus = driver.findElements(By.className("cx-status")).get(3).getText().toUpperCase().trim();
        Assert.assertEquals(actualAppStatus, expectedAppStatus);
    }

    //@Test
    public void _006_check_app_status_live(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        String expectedAppName = "DEVCONTENT_APP_LIVE";
        String actualAppName = driver.findElement(By.xpath("//*[contains(text(), 'DevContent_App_Live')]")).getText().toUpperCase().split(":")[0];
        Assert.assertEquals(actualAppName, expectedAppName);
        String expectedAppStatus = "LIVE";
        String actualAppStatus = driver.findElements(By.className("cx-status")).get(4).getText().toUpperCase().trim();
        Assert.assertEquals(actualAppStatus, expectedAppStatus);
    }

    @Test
    public void _007_my_apps_add_new_app_valid_header(){
        String expected = "ADD NEW APP";
        MyApps myApps = devUser.getMyAppsPage(driver);
        String actualTxt = driver.findElement(By.id("newAppButton")).getText();
        Assert.assertEquals(actualTxt.trim(), expected.trim());
        appDetails = myApps.clickAddNewAppBtn();
        String expectedWizHeader = "Add New App";
        WebElement addNewappWiz = driver.findElement(By.cssSelector(".col.mx-0.mx-sm-2.mx-lg-3.mt-1.pr-0"));
        isElementPresent(addNewappWiz);
        String actualWizHeader = addNewappWiz.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(actualWizHeader, expectedWizHeader);
        String expectedAppDtls = "App Details";
        WebElement wizardNewApp = driver.findElement(By.cssSelector(".col.mx-0.mx-sm-2.mx-lg-3.mt-1.pr-0"));
        String actualAppDtls = wizardNewApp.findElement(By.tagName("a")).getText().trim();
        Assert.assertEquals(actualAppDtls, expectedAppDtls);
    }

    @Test
    public void _008_my_apps_add_new_app_valid_text_appName() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedNameLbl = "Name";
        String actualNameLbl = newAppForm.findElement(By.tagName("label")).getText();
        Assert.assertEquals(actualNameLbl, expectedNameLbl);
        String expectedNamePlhdr = "Enter App Name";
        String actualNamePlhdr = newAppForm.findElement(By.id("appName")).getAttribute("placeholder");
        Assert.assertEquals(actualNamePlhdr, expectedNamePlhdr);

    }

    @Test
    public void _009_my_apps_add_new_app_valid_text_version() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedVerLbl = "Version";
        String actualVerLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Version')]")).getText();
        Assert.assertEquals(actualVerLbl, expectedVerLbl);
        String expectedVerPlhdr = "Enter App Version";
        String actualVerPlhdr = driver.findElement(By.id("appVersion")).getAttribute("placeholder");
        Assert.assertEquals(actualVerPlhdr, expectedVerPlhdr);


    }

    @Test
    public void _010_my_apps_add_new_app_valid_text_SubTitle() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedSubLbl = "Information";
        String actualSubLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Information')]")).getText();
        Assert.assertEquals(actualSubLbl, expectedSubLbl);
        String expectedSubPlhdr = "Enter the app subtitle, if applicable";
        String actualSubPlhdr = driver.findElement(By.id("appSubtitle")).getAttribute("placeholder");
        Assert.assertEquals(actualSubPlhdr, expectedSubPlhdr);

    }
    @Test
    public void _011_my_apps_add_new_app_valid_text_language() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedLangLbl = "Language";
        String actualLangLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Language')]")).getText();
        Assert.assertEquals(actualLangLbl, expectedLangLbl);
        WebElement LangDrpDwn = driver.findElements(By.className("multiselect-dropdown")).get(0);
        String expectedDrpdwnLangTxt = "Select Any Language this Application Supports";
        String actualDrpdwnLangTxt = LangDrpDwn.getText().trim();
        Assert.assertEquals(actualDrpdwnLangTxt, expectedDrpdwnLangTxt,"Failed to compare Drop Down Language Text");
        cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        if (! browserName.equals("microsoftedge")) {
            LangDrpDwn.click();
        }
        String expectedLangOpt1 = "English";
        String actualLangOpt1 = LangDrpDwn.findElements(By.tagName("li")).get(2).getText().trim();
        Assert.assertEquals(actualLangOpt1,expectedLangOpt1);
        String expectedLangOpt2 = "French";
        String actualLangOpt2 = LangDrpDwn.findElements(By.tagName("li")).get(3).getText().trim();
        Assert.assertEquals(actualLangOpt2,expectedLangOpt2);
        String expectedLangOpt3 = "Spanish";
        String actualLangOpt3 = LangDrpDwn.findElements(By.tagName("li")).get(4).getText().trim();
        Assert.assertEquals(actualLangOpt3,expectedLangOpt3);
//        String expectedLangOpt4 = "Danish";
//        String actualLangOpt4 = LangDrpDwn.findElements(By.className("multiselect-item-checkbox")).get(3).getText().trim();
//        Assert.assertEquals(actualLangOpt4,expectedLangOpt4);
//        String expectedLangOpt5 = "Italian";
//        String actualLangOpt5 = LangDrpDwn.findElements(By.className("multiselect-item-checkbox")).get(4).getText().trim();
//        Assert.assertEquals(actualLangOpt5,expectedLangOpt5);
        if (! browserName.equals("microsoftedge")) {
            LangDrpDwn.click();
        }

    }

    @Test
    public void _012_my_apps_add_new_app_valid_text_category() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedCatgLbl = "Category";
        String actualCatLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Category')]")).getText();
        Assert.assertEquals(actualCatLbl, expectedCatgLbl);
        String expectedDrpdwnCatTxt = "Select categories";
        WebElement CatDrpDwn = driver.findElements(By.className("multiselect-dropdown")).get(1);
        String actualDrpdwncatTxt = CatDrpDwn.getText().trim();
        Assert.assertEquals(actualDrpdwncatTxt, expectedDrpdwnCatTxt);
        cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        if (! browserName.equals("microsoftedge")) {
            CatDrpDwn.click();

        }
        wait(3000);
        String expectedCatOpt1 = "Merchandising";
        String actualCatOpt1 = CatDrpDwn.findElements(By.tagName("li")).get(2).getText().trim();
        Assert.assertEquals(actualCatOpt1,expectedCatOpt1);
        String expectedCatOpt2 = "Interactive";
        String actualCatOpt2 = CatDrpDwn.findElements(By.tagName("li")).get(3).getText().trim();
        Assert.assertEquals(actualCatOpt2,expectedCatOpt2);
        if (!browserName.equals("microsoftedge")) {
            CatDrpDwn.click();

        }

    }
    @Test
    public void _013_my_apps_add_new_app_valid_text_availability() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedAvlLbl = "Availability";
        String actualAvlLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Availability')]")).getText().trim();
        Assert.assertEquals(actualAvlLbl, expectedAvlLbl);
        WebElement avalDrp = driver.findElement(By.id("appAvailability"));
        String expectedDrpdwnAvlTxt = "Choose Availability";
        String actualDrpdwnAvlTxt = avalDrp.findElements(By.tagName("option")).get(0).getText().trim();
        Assert.assertEquals(actualDrpdwnAvlTxt, expectedDrpdwnAvlTxt);
        avalDrp.click();
        String expectedOpt1 = "Private";
        String actualDrpdwnopt1 = avalDrp.findElements(By.tagName("option")).get(1).getText();
        avalDrp.findElements(By.tagName("option")).get(1).click();
        Assert.assertEquals(actualDrpdwnopt1,expectedOpt1);
        String expectedOpt2 = "Public";
        String actualDrpdwnopt2 = avalDrp.findElements(By.tagName("option")).get(2).getText();
        Assert.assertEquals(actualDrpdwnopt2,expectedOpt2);
    }

    @Test
    public void _014_my_apps_add_new_app_valid_text_availability() {
        WebElement newAppForm = driver.findElement(By.cssSelector(".form-group"));
        String expectedRtlLbl = "Retailer Access";
        String actualRtlLbl = newAppForm.findElement(By.xpath("//*[contains(text(), 'Retailer Access')]")).getText();
        Assert.assertEquals(actualRtlLbl, expectedRtlLbl);
        WebElement RtlDrpDwn =  driver.findElements(By.className("multiselect-dropdown")).get(2);
        String expectedDrpdwnRtlTxt = "Select Retailers";
        String actualDrpdwnRtlTxt = RtlDrpDwn.getText().trim();
        Assert.assertEquals(actualDrpdwnRtlTxt, expectedDrpdwnRtlTxt);
    }

    @Test
    public void _015_my_apps_add_new_app_valid_text_pricing() {
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
        WebElement prcDrpDwn = driver.findElement(By.id("appPriceType"));
        String expectedTypOpt1 = "Please choose pricing type";
        String actualTypOpt1 = prcDrpDwn.findElements(By.tagName("option")).get(0).getText().trim();
        Assert.assertEquals(actualTypOpt1,expectedTypOpt1);
        cap = ((RemoteWebDriver) driver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        if (! browserName.equals("microsoftedge")) {
            prcDrpDwn.click();
        }
        String expectedTypOpt2 = "Per dispenser per month";
        String actualTypOpt2 = prcDrpDwn.findElements(By.tagName("option")).get(1).getText().trim();
        Assert.assertEquals(actualTypOpt2,expectedTypOpt2);
        String expectedTypOpt3 = "Per dispenser per year";
        String actualTypOpt3 = prcDrpDwn.findElements(By.tagName("option")).get(2).getText().trim();
        Assert.assertEquals(actualTypOpt3,expectedTypOpt3);
        String expectedTypOpt4 = "Per site per year";
        String actualTypOpt4 = prcDrpDwn.findElements(By.tagName("option")).get(3).getText().trim();
        Assert.assertEquals(actualTypOpt4,expectedTypOpt4);
        String expectedTypOpt5 = "One-time flat fee";
        String actualTypOpt5 = prcDrpDwn.findElements(By.tagName("option")).get(4).getText().trim();
        Assert.assertEquals(actualTypOpt5,expectedTypOpt5);
        if (! browserName.equals("microsoftedge")) {
            prcDrpDwn.click();
        }
        uploadCode = appDetails.setUpAppDetailsFromPropFile("app_details.properties");



    }

    @Test
    public void _016_add_new_app_code_valid_texts() {
        String expectedHdr = "Add New App";
        WebElement newAppForm = driver.findElement(By.cssSelector(".col.mx-0.mx-sm-2.mx-lg-3.mt-1.pr-0"));
        String actualHdr = newAppForm.findElement(By.tagName("h2")).getText();
        Assert.assertEquals(actualHdr,expectedHdr);
        String expectedUpldBtn = "UPLOAD";
        String actualUpldBtn = driver.findElement(By.id("newAppUploadCode")).getText().trim();
        Assert.assertEquals(actualUpldBtn,expectedUpldBtn);
        marketing = uploadCode.upload(zipFile);
    }
    @Test
    public void _017_add_new_app_marketing_valid_texts() {
        wait(1000);
        String expectedPrmLbl = "Promotional Text";
        String actualPrmLbl = driver.findElement(By.xpath("//*[contains(text(), 'Promotional Text')]")).getText().trim();
        Assert.assertEquals(actualPrmLbl,expectedPrmLbl);
        String expectedPrmTxt = "Enter the promotional text";
        String actualPrmTxt = driver.findElement(By.id("app-promotion")).getAttribute("placeholder");
        Assert.assertEquals(actualPrmTxt,expectedPrmTxt);
        String expectedKwdLbl = "Keywords";
        String actualKwdLbl = driver.findElement(By.xpath("//*[contains(text(), 'Keywords')]")).getText().trim();
        Assert.assertEquals(actualKwdLbl,expectedKwdLbl);
        String expectedKwdTxt = "Enter keywords separated by a comma";
        String actualKwdTxt = driver.findElement(By.id("app-keywords")).getAttribute("placeholder");
        Assert.assertEquals(actualKwdTxt,expectedKwdTxt);
        String expectedIcnLbl = "App Icon*";
        WebElement MarketingStep = driver.findElement(By.cssSelector(".ng-untouched.ng-pristine.ng-valid"));
        String actualIcnLbl = MarketingStep.findElement(By.xpath("//*[contains(text(), 'Icon')]")).getText().trim();
        Assert.assertEquals(actualIcnLbl,expectedIcnLbl);
        String expectedIcnSiz = "72 x 72";
        String  actualIcnSiz = driver.findElement(By.id("newAppUploadIcon")).getText().trim();
        Assert.assertEquals(actualIcnSiz,expectedIcnSiz);
        String expectedAppPrv = "App Preview + Screenshots*";
        String actualAppPrv = driver.findElement(By.xpath("//*[contains(text(), 'App Preview + Screenshots')]")).getText().trim();
        Assert.assertEquals(expectedAppPrv,actualAppPrv);
        String expectedAppPrvScr = "800 x 400px";
        String actualAppPrvScr = driver.findElement(By.id("newAppUploadScreenshot")).getText().trim();
        Assert.assertEquals(actualAppPrvScr,expectedAppPrvScr);
        WebElement cancelBtn = driver.findElement(By.id("cancelButton"));
        scrollDown(cancelBtn);
        cancelBtn.click();

    }
    @Test
    public void _018_my_apps_new_version_valid_text(){
        String expected = "NEW VERSION";
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(0);
        String actualTxt = driver.findElement(By.id("editAppNewVersion")).getText().trim();
        Assert.assertEquals(expected.trim(),actualTxt.trim());
        showUp.backToMyApps();
    }
   @Test
    public void _019_my_apps_what_new_in_this_version_valid_text(){
        String expected = "What's new in this version?";
        MyApps myApps = devUser.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.className("mb-2")).getText().trim();
        Assert.assertEquals(expected,actualTxt);
        showUp.backToMyApps();
    }
    @Test
    public void _020_my_apps_version_valid_texts(){
        String expected = "Versions";
        MyApps myApps = devUser.getMyAppsPage(driver);
        wait(1000);
        myApps.showUp(0);
        WebElement appsVersions = driver.findElement(By.cssSelector(".ui-body-main.container-fluid.mt-2.mt-lg-0.pt-2.px-3"));
        isElementPresent(appsVersions);
        String actualTxt = appsVersions.findElements(By.xpath("//*[contains(text(), 'Versions')]")).get(2).getText().trim();
        Assert.assertEquals(actualTxt,expected);
        String expectedHeader = "App Versions";
        String actualHeader = driver.findElement(By.cssSelector(".col.row.mx-0.mx-sm-2.mx-lg-3.mt-1")).getText().split(" -")[0];
        Assert.assertEquals(actualHeader,expectedHeader);
        showUp.backToMyApps();

    }
    @Test
    public void _021_my_apps_back_to_my_apps_valid_text() {
        String expected = "Back to My Apps";
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(0);
        String actualTxt = driver.findElement(By.className("nav-text")).getText();
        showUp.backToMyApps();
        Assert.assertEquals(expected, actualTxt);

    }

    //@Test Dashboard not clickable
    public void _022_dev_home_page_valid_Dashboard_header(){
        String expected = "Dashboard";
        devUser.getDashboardPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
        String expectedSdbr = "DASHBOARD";
        String actualSdbr = driver.findElement(By.id("navItem4")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);


    }
    //@Test Transaction history not clickable
    public void _023_dev_home_page_valid_TransactionHistory_header(){
        String expected = "Transaction History";
        devUser.getTrasactionHistoryPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
        String expectedSdbr = "TRANSACTION HISTORY";
        String actualSdbr = driver.findElement(By.id("navItem6")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);
    }

    //@Test RevenueReport not clickable
    public void _024_home_page_valid_RevenueReport_header(){
        String expected = "Revenue Report";
        devUser.getRevenueReportPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText().trim();
        Assert.assertEquals(actualTxt,expected);
        String expectedSdbr = "REVENUE REPORT";
        String actualSdbr = driver.findElement(By.id("navItem7")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);

    }
    @Test
    public void _025_home_page_valid_SupportTickets_header(){
        String expected = "Support Tickets";
        devUser.getSupportTicketstPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText().trim();
        Assert.assertEquals(actualTxt,expected);
        String expectedSdbr = "SUPPORT TICKETS";
        String actualSdbr = driver.findElement(By.id("navItem8")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);

    }
    //@Test Publish profile not clickable
    public void _026_home_page_valid_PublicProfile_header(){
        String expected = "Public Profile";
        devUser.getPublicProfilePage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText().trim();
        Assert.assertEquals(actualTxt,expected);
        String expectedSdbr = "PUBLIC PROFILE";
        String actualSdbr = driver.findElement(By.id("navItem9")).getText().toUpperCase().trim();
        Assert.assertEquals(actualSdbr,expectedSdbr);

    }

}