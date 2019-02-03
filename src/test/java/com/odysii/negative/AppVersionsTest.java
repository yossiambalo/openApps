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
        user = new User(driver);
        DEV_USER_NAME = "auto.open.apps@gmail.com";
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_1,null);
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);

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
