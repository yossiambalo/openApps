package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.AppDetails;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.myApps.helper.appDetails.CategoryType;
import com.odysii.selenium.page.myApps.helper.appDetails.LanguageType;
import com.odysii.selenium.page.myApps.helper.appDetails.Retailer;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyAppsTestRoi extends TestBase {

    private final int WAIT = 2000;
    @BeforeClass
    public void init(){
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
        // Testing empty values under the tested field.
    }
    @Test
    public void _001_name_field_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("","LALALA",LanguageType.ENGLISH, CategoryType.SPORTS, AvailabilityType.PRIVATE, Retailer.SHELL);
        //String expectedValue ="custom-file-input";
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue,"Test failed - no validation of mandatory name field");
    }
     @Test
    public void _002_subtitle_field_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Roi","",LanguageType.ITALIAN, CategoryType.NEWS_AND_WEATHER, AvailabilityType.PRIVATE, Retailer.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory subtitle field ");
     }
     @Test
    public void _003_language_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Yossi","Arau","", CategoryType.SPORTS, AvailabilityType.PRIVATE,Retailer.MOBILE);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory language field");
     }
     @Test
    public void _004_category_is_empty_negative(){
         HomePage homePage = new HomePage(driver);
         MyApps myApps = homePage.getMyAppsPage(driver);
         AppDetails appDetails = myApps.clickAddNewAppBtn();
         appDetails.setUpAppDetails("Liron", "IT",LanguageType.DANISH, "",AvailabilityType.PRIVATE, Retailer.SHELL);
         Boolean actualValue = isElementExist(By.className("custom-file-input"));
         Assert.assertFalse(actualValue, "Test failed - no validation of mandatory category field");
    }
    @Test
    public void _005_availability_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("XXX","KKK", LanguageType.FRENCH,CategoryType.SPORTS, "",Retailer.SHELL);
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory availability field");

    }
    @Test
    public void _006_retailer_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails("Evgeny", "Lev", LanguageType.FRENCH, CategoryType.SPORTS, AvailabilityType.PRIVATE, "");
        Boolean actualValue = isElementExist(By.className("custom-file-input"));
        Assert.assertFalse(actualValue, "Test failed - no validation of mandatory retailer field");
        }
        // Testing max chars, special chars, languages.

     @Test
    public void _007_name_field_length_257_chars_negative(){
         HomePage homePage = new HomePage(driver);
         MyApps myApps = homePage.getMyAppsPage(driver);
         AppDetails appDetails = myApps.clickAddNewAppBtn();
         appDetails.setUpAppDetails("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567","Max is 256 chars",LanguageType.DANISH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
         Boolean actualValue = isElementExist(By.className("custom-file-input"));
         Assert.assertFalse(actualValue, "Test failed - no validation of max characters in name field");
     }
     @Test
    public void _008_subtitle_field_length_255_chars_positive(){
         HomePage homePage = new HomePage(driver);
         MyApps myApps = homePage.getMyAppsPage(driver);
         AppDetails appDetails = myApps.clickAddNewAppBtn();
         appDetails.setUpAppDetails("345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567","Max is 256 chars",LanguageType.DANISH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
         Boolean actualValue = isElementExist(By.className("custom-file-input"));
         Assert.assertTrue(actualValue,"Subtitle can contain 255 chars");

     }
     @Test
    public void _009_name_field_special_chars_negative(){
         HomePage homePage = new HomePage(driver);
         MyApps myApps = homePage.getMyAppsPage(driver);
         AppDetails appDetails = myApps.clickAddNewAppBtn();
         appDetails.setUpAppDetails("@!*#$%!","+-/*",LanguageType.DANISH,CategoryType.SPORTS,AvailabilityType.PRIVATE, Retailer.SHELL);
         Boolean actualValue = isElementExist(By.className("custom-file-input"));
         Assert.assertFalse(actualValue, "Test failed - no validation of special characters in name field");

     }

    }

