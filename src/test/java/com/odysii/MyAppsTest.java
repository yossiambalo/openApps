package com.odysii;

import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.openApps.helper.appDetails.CategoryType;
import com.odysii.selenium.page.openApps.helper.appDetails.LanguageType;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyAppsTest extends TestBase{
    private AppDetails appDetails;
    private final String zipFile = "dog2.jpg";
    private final String screenShotFile = "bike.jpg";

    DevHomePage devUser;
    @BeforeClass
    public void login(){
        devUser = new DevHomePage(driver);
        devUser.login("user","123456");
    }
    @Test(enabled = false)
    public void _001_add_new_app(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        int expectedValue = driver.findElements(By.className("card")).size()+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppDetails();
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        int actualValue = driver.findElements(By.className("card")).size();
        Assert.assertEquals(expectedValue,actualValue,"Expected value not equals to actual value");
    }

    @Test
    public void _002_name_field_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("", "LALALA", LanguageType.ENGLISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        //String expectedValue ="custom-file-input";
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory name field");
    }

    @Test
    public void _003_subtitle_field_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Roi", "", LanguageType.ITALIAN, CategoryType.NEWS_AND_WEATHER, AvailabilityType.PRIVATE, RetailerType.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory subtitle field ");
    }

    @Test
    public void _004_language_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Yossi", "Arau", "", CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory language field");
    }

    @Test
    public void _005_category_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Liron", "IT", LanguageType.DANISH, "", AvailabilityType.PRIVATE, RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory category field");
    }

    @Test
    public void _006_availability_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("XXX", "PP", LanguageType.FRENCH, CategoryType.SPORTS, "", RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory availability field");
    }

    @Test
    public void _007_retailer_is_empty_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Evgeny", "Lev", LanguageType.FRENCH, CategoryType.SPORTS, AvailabilityType.PRIVATE, "");
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory retailer field");
    }
    // Testing max chars, special chars, languages.

    @Test(enabled = false)//No limition for chars
    public void _008_name_field_length_257_chars_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "Max is 256 chars", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of max characters in name field");
    }

    @Test
    public void _009_subtitle_field_length_255_chars_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "Max is 256 chars", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertTrue(actualValue, "Subtitle can contain 255 chars");
    }

    @Test//TODo: ask devs should be validation for those chars?
    public void _010_valid_name_field_special_chars_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("@!*#$%!", "+-/*", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of special characters in name field");
    }

    @Test
    public void _011_cancel_button_app_details_is_function_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.cancel();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertTrue(actualValue, "Cancel button function and back to My apps page");
    }

    @Test(enabled = false)//No limitetion for chars
    public void _012_language_input_name_field_chinese_128_chars_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对断对", "Max is 256 chars", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Japanese is supported");
    }

    @Test(enabled = false)//No limitation for chars
    public void _013_language_input_subtitle_field_hebrew_128_chars_positive() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("hebrew test", "אני כותב טקסט בעברית... פםדחגפסךןקג]םח ]םחקגךלמאני כותב טקסט בעברית... פםדחגפסךןקג]םח ]םחקגךלמאני כותב טקסט בעברית... פםדחגפסךן", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Hebrew is supported");
    }

    @Test
    public void _014_back_button_upload_code(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("test", "scvs", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        WebElement backButton = driver.findElement(By.xpath("//*[contains(text(), '"+ backTxt +"')]"));
        backButton.click();
        Boolean actualValue = isElementExist(By.id("app-name"));
        Assert.assertTrue(actualValue,"back button is function");
    }

    @Test
    public void _015_cancel_button_upload_code_page() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("test2", "scdvdvs", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, RetailerType.SHELL);
        WebElement cancelButton = driver.findElement(By.id(cancelID));
        cancelButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertTrue(actualValue, "Previous button is function");
    }

    @Test
    public void _016_continue_button_upload_code_page_positive(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, RetailerType.SHELL);
        wait(WAIT);
        uploadCode.upload(zipFile);
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        Assert.assertTrue(actualValue,"Moved to marketing page successfully");
    }

    @Test
    public void _017_promotional_text_empty_marketing_page_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, RetailerType.SHELL);
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing("", "Keyword field", screenShotFile, zipFile);
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "Promotional token field is mandatory - it's empty!");
    }

    @Test
    public void _018_keywords_empty_marketing_page_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, RetailerType.SHELL);
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing("P_Token", "", screenShotFile, zipFile);
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue,"No validation for field!");
    }

    @Test
    public void _019_screenshot_file_path_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, RetailerType.SHELL);
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing("P_Token", "sfg", "", zipFile);
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue,"No validation for field!");
    }

    @Test
    public void _020_appicon_path_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, RetailerType.SHELL);
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing("P_Token", "sfg", screenShotFile, "");
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue,"No validation for field!");

    }
    @AfterMethod
    public void afterMethod(){
        appDetails.cancel();
        driver.navigate().refresh();
    }
}
