package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase {
    DevHomePage devHomePage;
    final String APP_CLASS_NAME = "Negative App Version";
    @BeforeClass
    public void login() {
        user = new User(driver);
        devHomePage = (DevHomePage) user.login("user", "123456", UserType.DEVELOPER);
        category = "App Versions";
    }

    @Test
    public void _001_version_field_without_value_negative() {
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        showUp.getAppVersion();
        WebElement continueButton = driver.findElement(By.id("nextButton"));
        scrollDown(continueButton);
        wait(WAIT);
        continueButton.click();
        Boolean actualValue = isElementExist(By.className("invalid-feedback"));
        Assert.assertTrue(actualValue, "Version is required or is in wrong format.");

    }
}
