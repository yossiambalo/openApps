package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.AppVersion;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.summary.ShowUp;
import com.odysii.selenium.page.myApps.summary.Summary;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase{

    @BeforeClass
    public void beforeTest() {
        login("user", "123456");
    }

    @Test
    public void _001_app_version_is_empty_negative(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        AppVersion appVersion = showUp.getAppVersions();
        //appVersion.editAppVersion(FieldType.NEW_VERSION_NUMBER,"");
       // appVersion.editAppVersion(FieldType.NEW_VERSION_TEXT, "Test - app version is empty");
        appVersion.editAppVersion("", "TEST","C:\\Users\\roi.avital\\Desktop\\Project's utilities\\IMAGES for pump\\1030084019.png");

    }

}
