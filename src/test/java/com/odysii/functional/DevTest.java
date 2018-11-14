package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.AppDetails;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.UploadCode;
import com.odysii.selenium.page.openApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.openApps.helper.appDetails.CategoryType;
import com.odysii.selenium.page.openApps.helper.appDetails.LanguageType;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class DevTest extends TestBase {
    private final static String DEV_USER_NAME = "user";
    private final static String DEV_USER_PASS = "123456";
    private final String zipFile = "TH.zip";
    User user;
    DevHomePage devHomePage;
    MyApps myApps;


    @BeforeClass
    public void login() {
        user = new User(driver);
        devHomePage = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);

    }
    @Test
    public void _001_add_new_version_with_same_version_number(){
        myApps = devHomePage.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppDetails();
        UploadCode uploadCode = appDetails.setUpAppDetails();
        uploadCode.upload(zipFile);


    }


}
