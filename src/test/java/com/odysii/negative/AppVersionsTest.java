package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase {
    DevHomePage devHomePage;
    @BeforeClass
    public void prepare(){
        if (!isPrepared){
            prepareTest("app_details_DevContent_PreSubmitted.properties", ApplicationStatus.PRESUBMITTED);
            isPrepared = true;
        }else {
            user = new User(driver);
            devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        }
        category = "App Versions";

    }

    @Test
    public void _001_version_field_without_value_negative() {
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(0);
        showUp.getAppVersion();
        WebElement continueButton = driver.findElement(By.id("nextButton"));
        scrollDown(continueButton);
        wait(WAIT);
        continueButton.click();
        Boolean actualValue = isElementExist(By.className("invalid-feedback"));
        Assert.assertTrue(actualValue, "Version is required or is in wrong format.");

    }
}
