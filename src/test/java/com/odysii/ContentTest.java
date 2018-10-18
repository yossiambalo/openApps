package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ContentTest extends TestBase{
    private DevHomePage devPage;
    private User user;
    @BeforeClass
    private void login(){
        user = new User(driver);
        devPage = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
    }
    @Test
    public void _001_valid_Dashboard_header(){
        String expected = "Dashboard";
        devPage.getDashboardPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _002_valid_MyApps_header(){
        String expected = "My Apps";
        devPage.getMyAppsPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _003_valid_TransactionHistory_header(){
        String expected = "Transaction History";
        devPage.getTrasactionHistoryPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _004_valid_RevenueReport_header(){
        String expected = "Revenue Report";
        devPage.getRevenueReportPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _005_valid_SupportTickets_header(){
        String expected = "Support Tickets";
        devPage.getSupportTicketstPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    @Test
    public void _006_valid_PublicProfile_header(){
        String expected = "Public Profile";
        devPage.getPublicProfilePage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
}
