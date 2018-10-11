package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContentTest extends TestBase{
    DevHomePage adminPage;
    User user;
    @BeforeMethod
    public void login(){
        user = new User(driver);
        adminPage = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
    }
    @Test
    public void _001_valid_Dashboard_header(){
        String expected = "Dashboard";
        adminPage.getDashboardPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
}
