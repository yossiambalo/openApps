package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.AppDetails;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.UploadCode;
import com.odysii.selenium.page.myApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.myApps.helper.appDetails.CategoryType;
import com.odysii.selenium.page.myApps.helper.appDetails.LanguageType;
import com.odysii.selenium.page.myApps.helper.appDetails.Retailer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAppsTestRoiDebug extends TestBase{
    private final int WAIT = 2000;
    private final String cancelTxt = "CANCEL";
    private final String backTxt = "BACK";
    private final String continueTxt = "CONTINUE";
    private final String finishTxt = "FINISH";

    @BeforeClass
    public void init(){
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
    }

    @Test
    public void _001_name_field_is_empty_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("", "LALALA", LanguageType.ENGLISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        //String expectedValue ="custom-file-input";
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory name field");
    }

    @Test
    public void _002_subtitle_field_is_empty_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Roi", "", LanguageType.ITALIAN, CategoryType.NEWS_AND_WEATHER, AvailabilityType.PRIVATE, Retailer.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory subtitle field ");
    }

    @Test
    public void _003_language_is_empty_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Yossi", "Arau", "", CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory language field");
    }

    @Test
    public void _004_category_is_empty_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Liron", "IT", LanguageType.DANISH, "", AvailabilityType.PRIVATE, Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory category field");
    }

    @Test
    public void _005_availability_is_empty_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("XXX", "KKK", LanguageType.FRENCH, CategoryType.SPORTS, "", Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory availability field");
    }

    @Test
    public void _006_retailer_is_empty_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Evgeny", "Lev", LanguageType.FRENCH, CategoryType.SPORTS, AvailabilityType.PRIVATE, "");
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory retailer field");
    }
    // Testing max chars, special chars, languages.

    @Test
    public void _007_name_field_length_257_chars_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "Max is 256 chars", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of max characters in name field");
    }

    @Test
    public void _008_subtitle_field_length_255_chars_positive() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567", "Max is 256 chars", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertTrue(actualValue, "Subtitle can contain 255 chars");
    }

    @Test
    public void _009_name_field_special_chars_negative() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("@!*#$%!", "+-/*", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of special characters in name field");
    }

    @Test
    public void _010_cancel_button_app_details_is_function_positive() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        WebElement cancelButton = driver.findElement(By.xpath("//*[contains(text(), '"+ cancelTxt +"')]"));
        cancelButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertTrue(actualValue, "Cancel button function and back to My apps page");
    }

    @Test
    public void _011_language_input_name_field_chinese_128_chars_positive() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对品卞光郎竹彡助断对断对", "Max is 256 chars", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Japanese is supported");
    }

    @Test
    public void _012_language_input_subtitle_field_hebrew_128_chars_positive() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("hebrew test", "אני כותב טקסט בעברית... פםדחגפסךןקג]םח ]םחקגךלמאני כותב טקסט בעברית... פםדחגפסךןקג]םח ]םחקגךלמאני כותב טקסט בעברית... פםדחגפסךן", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Hebrew is supported");
    }

    @Test
    public void _013_back_button_upload_code(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("test", "scvs", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        WebElement backButton = driver.findElement(By.xpath("//*[contains(text(), '"+ backTxt +"')]"));
        backButton.click();
        Boolean actualValue = isElementExist(By.id("app-name"));
        Assert.assertTrue(actualValue,"back button is function");
    }

    @Test
    public void _014_cancel_button_upload_code_page() {
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("test2", "scdvdvs", LanguageType.DANISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        WebElement cancelButton = driver.findElement(By.xpath("//*[contains(text(), '"+ cancelTxt +"')]"));
        cancelButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertTrue(actualValue, "Previous button is function");
    }

    @Test
    public void _015_continue_button_upload_code_page_positive(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
        wait(WAIT);
        uploadCode.upload();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), '"+ continueTxt +"')]"));
        Assert.assertTrue(actualValue,"Moved to marketing page successfully");
    }

    @Test
    public void _016_promotional_text_empty_marketing_page_negtive(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
        wait(WAIT);
        uploadCode.upload();
        fillMarketing("", "Keyword field", "C:\\yossi\\dog2.jpg", "C:\\yossi\\dog3.jpg");
        Boolean actualValue = isClickable(By.xpath("//*[contains(text(), '"+finishTxt+"')]"));
        Assert.assertFalse(actualValue, "Promotional token field is mandatory - it's empty!");
    }

    @Test
    public void _017_keywords_empty_marketing_page_negtive(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
        wait(WAIT);
        uploadCode.upload();
        fillMarketing("P_Token", "", "C:\\yossi\\dog2.jpg", "C:\\yossi\\dog3.jpg");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        Boolean actualValue = isClickable(By.xpath("//*[contains(text(), 'FINISH')]"));
        Assert.assertFalse(actualValue,"Button isn't clickable!");
    }

    @Test
    public void _018_screenshot_file_path_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
        wait(WAIT);
        uploadCode.upload();
        fillMarketing("P_Token", "sfg", "", "C:\\yossi\\dog3.jpg");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        Boolean actualValue = isClickable(By.xpath("//*[contains(text(), 'FINISH')]"));
        Assert.assertFalse(actualValue,"Button isn't clickable!");
    }

    @Test
    public void _019_appicon_path_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails("Kadlj","sss",LanguageType.FRENCH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
        wait(WAIT);
        uploadCode.upload();
        fillMarketing("P_Token", "sfg", "C:\\yossi\\dog3.jpg", "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        Boolean actualValue = isClickable(By.xpath("//*[contains(text(), 'FINISH')]"));
        Assert.assertFalse(actualValue,"Button isn't clickable!");

    }
}
