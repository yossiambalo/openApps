package com.odysii;

import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.AppVersion;
import com.odysii.selenium.page.myApps.User;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.summary.ShowUp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase{

    HomePage homePage;
    @BeforeClass
    public void login() {
        User login = new User(driver);
        login.login("user", "123456",false);
    }
    @Test
    public void _001_app_version_is_empty_negative(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        AppVersion appVersion = showUp.getAppVersions();
        //appVersion.editAppVersion(FieldType.NEW_VERSION_NUMBER,"");
       // appVersion.editAppVersion(FieldType.NEW_VERSION_TEXT, "Test - app version is empty");
        appVersion.editAppVersion("", "TEST","C:\\yossi\\dog2.jpg");
    }

}
