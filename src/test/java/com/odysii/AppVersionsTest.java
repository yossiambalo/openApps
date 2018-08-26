package com.odysii;

import com.odysii.selenium.page.openApps.dev.AppVersion;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase{
    DevHomePage devUser;
    @BeforeClass
    public void login(){
        devUser = new DevHomePage(driver);
        devUser.login("user","123456");
    }
    @Test
    public void _001_app_version_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        AppVersion appVersion = showUp.getAppVersions();
        //appVersion.editAppVersion(FieldType.NEW_VERSION_NUMBER,"");
       // appVersion.editAppVersion(FieldType.NEW_VERSION_TEXT, "Test - app version is empty");
        appVersion.editAppVersion("", "TEST","C:\\yossi\\dog2.jpg");
    }

}
