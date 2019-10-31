package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.Availabilty;
import com.odysii.selenium.page.openApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.openApps.helper.appDetails.CategoryType;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class MyAppsTest extends TestBase {
    private AppDetails appDetails;
    DevHomePage devUser;
    private Marketing marketing;

    @BeforeClass
    public void login(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        List<String> retailers = new ArrayList<>();
        retailers.add(RetailerType.SHELL);
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_3,retailers);
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        category = "My Apps";

    }

    @Test
    public void _001_name_field_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("","1.2.5","Subtitle",PriceType.PER_SITE_PER_YEAR,"3",Availabilty.PRIVATE);
        //String expectedValue ="custom-file-input";
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory name field");
    }

    @Test (enabled = false)
    public void _002_subtitle_field_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Roi", "1.0.2","", PriceType.ON_TIME_FLAT_FEE, "5", Availabilty.PUBLIC);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory subtitle field ");
    }

    @Test (enabled = false)
    public void _003_language_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Yossi","1.0.2", "Arau", CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory language field");
    }

    @Test (enabled = false)
    public void _004_category_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Liron","1.0.2", "IT", PriceType.PER_DISPENSER_PER_YEAR,"3",Availabilty.PRIVATE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory category field");
    }

    @Test
    public void _005_availability_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("XXX","1.0.2", "PP",PriceType.PER_DISPENSER_PER_MONTH,"4",null);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory availability field");

    }

    @Test (enabled = false)
    public void _008_retailer_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Evgeny", "1.0.2","Lev", CategoryType.SPORTS, AvailabilityType.PRIVATE, "");
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory retailer field");
    }
    // Testing max chars, special chars, languages.

    @Test(enabled = false)//No limitation for chars
    public void _009_name_field_length_257_chars_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "1.0.2","Max is 256 chars", PriceType.PER_DISPENSER_PER_YEAR,"3",Availabilty.PRIVATE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of max characters in name field");
    }

    @Test (enabled = false)//No limition for chars
    public void _010_subtitle_field_length_255_chars_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "1.0.2","Max is 256 chars",PriceType.PER_DISPENSER_PER_YEAR,"3",Availabilty.PRIVATE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertTrue(actualValue, "Subtitle can contain 255 chars");
    }

    @Test (enabled = false) //TODo: ask devs should be validation for those chars?
    public void _011_valid_name_field_special_chars_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("@!*#$%!","1.0.2", "+-/*",PriceType.ON_TIME_FLAT_FEE,"77",Availabilty.PUBLIC);
        Boolean actualValue = isElementExist(By.id("newAppUploadCode"));
        Assert.assertFalse(actualValue, "Test failed - no validation of special characters in name field");
    }

    @Test
    public void _012_cancel_button_app_details_is_function_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        WebElement continueButton = driver.findElement(By.id("nextButton"));
        scrollDown(continueButton);
        appDetails.cancel();
        Boolean actualValue = isElementExist(By.id("newAppButton"));
        Assert.assertTrue(actualValue, "Cancel button function and back to My apps page");
    }

    @Test(enabled = false)//No limitetion for chars
    public void _013_language_input_name_field_chinese_128_chars_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对断对","1.0.2", "Max is 256 chars",PriceType.PER_DISPENSER_PER_YEAR,"66",Availabilty.PRIVATE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Japanese is supported");
    }

    @Test(enabled = false)//No limitation for chars
    public void _014_language_input_subtitle_field_hebrew_128_chars_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("hebrew test","1.0.2", "אני כותב טקסט בעברית... פםדחגפסךןקג]םח ]םחקגךלמאני כותב טקסט בעברית... פםדחגפסךןקג]םח ]םחקגךלמאני כותב טקסט בעברית... פםדחגפסךן",PriceType.PER_SITE_PER_YEAR,"3",Availabilty.PRIVATE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Hebrew is supported");
    }

    @Test
    public void _015_back_button_upload_code(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("test","1.0.2", "scvs",PriceType.PER_DISPENSER_PER_YEAR,"3",Availabilty.PRIVATE);
        WebElement backButton = driver.findElement(By.id("previousButton"));
        backButton.click();
        wait(WAIT);
        Boolean actualValue = isElementExist(By.id("appName"));
        Assert.assertTrue(actualValue,"back button is function");
    }

    @Test
    public void _016_cancel_button_upload_code_page() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("test2","1.0.2", "scdvdvs", PriceType.PER_DISPENSER_PER_YEAR,"3",Availabilty.PRIVATE);
        WebElement cancelButton = driver.findElement(By.id("cancelButton"));
        cancelButton.click();
        wait(WAIT);
        Boolean actualValue = isElementExist(By.id("appName"));
        Assert.assertTrue(actualValue, "Previous button is function");
    }

    @Test
    public void _017_continue_button_upload_code_page_positive(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Test Button Upload Code Positive","8.8.18","YNWA",PriceType.PER_DISPENSER_PER_MONTH,"8",Availabilty.PRIVATE);
        wait(WAIT);
        WebElement agreeAndUpload = driver.findElement(By.id("codeFile"));
        marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        Boolean actualValue = isElementExist(By.id("app-promotion"));
        Assert.assertTrue(actualValue,"Moved to marketing page successfully");
    }

    @Test
    public void _018_promotional_text_empty_marketing_page_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","1.0.2","sss",PriceType.PER_SITE_PER_YEAR,"32",Availabilty.PRIVATE);
        wait(WAIT);
        marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing("","Stevie G","800x400.png","72-72.jpg", true);
        wait(WAIT);
        WebElement finishButton = driver.findElement(By.id("finishButton"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "Promotional text fields are mandatory - it's empty!");
    }

    @Test
    public void _019_keywords_empty_marketing_page_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","1.0.2","sss",PriceType.PER_SITE_PER_YEAR,"32",Availabilty.PRIVATE);
        wait(WAIT);
        marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing("King Kenny","","800x400.png","72-72.jpg",true);
        wait(WAIT);
        WebElement finishButton = driver.findElement(By.id("finishButton"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "Promotional text fields are mandatory - it's empty!");
    }

    @Test
    public void _020_screenshot_file_path_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","1.0.2","sss",PriceType.PER_SITE_PER_YEAR,"32",Availabilty.PRIVATE);
        wait(WAIT);
        marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing("PText","fff",null,"72-72.jpg",true);
        wait(WAIT);
        WebElement finishButton = driver.findElement(By.id("finishButton"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "Screenshot image is mandatory - it's empty!");
    }

    @Test
    public void _021_appicon_path_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","1.0.2","sss",PriceType.PER_SITE_PER_YEAR,"32",Availabilty.PRIVATE);
        wait(WAIT);
        marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing("King Kenny","Stevie G","800x400.png",null,true);
        wait(WAIT);
        WebElement finishButton = driver.findElement(By.id("finishButton"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "App icon image is mandatory - it's empty!");
    }

    @AfterMethod
    public void afterMethod(){
        driver.navigate().refresh();
    }
}
