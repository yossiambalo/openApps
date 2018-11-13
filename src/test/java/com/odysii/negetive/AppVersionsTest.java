package com.odysii.negetive;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.AppVersion;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase {
    DevHomePage devHomePage;
    User user;
    @BeforeClass
    public void login(){
        user = new User(driver);
        devHomePage = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
    }

    @Test
    public void _001_version_field_without_value_positive(){
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        AppVersion appVersion = showUp.getAppVersion();
        appVersion.clickNewVersion();
        WebElement continueButton = driver.findElement(By.id("nextButton"));
        continueButton.click();
    }

}
