package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
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
        prepareTest("app_details_DevContent_PreSubmitted.properties",ApplicationStatus.PRESUBMITTED);
        category = "AppVersionsTest";
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
        WebElement cancel = driver.findElement(By.id("cancelButton"));
        cancel.click();

    }
}
