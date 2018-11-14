package com.odysii.content;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test Prefixes:
 * Dev user: dev_home_page
 * My apps: my_apps
 * Admin: admin_support_ticket
 */

public class DevContentTest extends TestBase {
    DevHomePage devHomePage;
    User user;
    @BeforeClass
    public void login(){
        user = new User(driver);
        devHomePage = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);

    }
    @Test
    public void _001_dev_home_page_valid_MyApps_header(){
        String expected = "My Apps";
        devHomePage.getMyAppsPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);

    }
    @Test
    public void _002_my_apps_valid_app_status(){
       MyApps myApps = devHomePage.getMyAppsPage(driver);
        Assert.assertTrue((myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.SUBMITTED.getStatus().toLowerCase())
        || myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.PRESUBMITTED.getStatus().toLowerCase())
                || myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.CERTIFIED.getStatus().toLowerCase())
                || myApps.getApplicationStatus(0).toLowerCase().equals(ApplicationStatus.REJECT.getStatus().toLowerCase())));

    }
    @Test
    public void _003_my_apps_add_new_app_valid_text(){
        String expected = "ADD NEW APP";
        devHomePage.getMyAppsPage(driver);
        String actualTxt = driver.findElement(By.id("newAppButton")).getText();
        Assert.assertEquals(actualTxt.trim(),expected.trim());

    }
    @Test
    public void _004_my_apps_new_version_valid_text(){
        String expected = "NEW VERSION";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.id("editAppNewVersion")).getText();
        Assert.assertEquals(expected.trim(),actualTxt.trim());

    }
    @Test
    public void _005_my_apps_what_new_in_this_version_valid_text(){
        String expected = "What's new in this version?";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.className("mb-2")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _006_my_apps_version_valid_text(){
        String expected = "Versions";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.xpath("/html/body/open-apps/div[2]/div/div/div/div[2]/ng-component/app-versions/div[2]/div/div[2]/div[2]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }
    @Test
    public void _007_my_apps_back_to_my_apps_valid_text() {
        String expected = "Back to My Apps";
        MyApps myApps = devHomePage.getMyAppsPage(driver);
        myApps.showUp(0);
        String actualTxt = driver.findElement(By.className("nav-text")).getText();
        Assert.assertEquals(expected, actualTxt);

    }

    //@Test
    public void dev_home_page_valid_Dashboard_header(){
        String expected = "Dashboard";
        devHomePage.getDashboardPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    //@Test
    public void dev_home_page_valid_TransactionHistory_header(){
        String expected = "Transaction History";
        devHomePage.getTrasactionHistoryPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    //@Test
    public void home_page_valid_RevenueReport_header(){
        String expected = "Revenue Report";
        devHomePage.getRevenueReportPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    //@Test
    public void home_page_valid_SupportTickets_header(){
        String expected = "Support Tickets";
        devHomePage.getSupportTicketstPage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }
    //@Test
    public void home_page_valid_PublicProfile_header(){
        String expected = "Public Profile";
        devHomePage.getPublicProfilePage(driver);
        String actualTxt = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(actualTxt,expected);
    }

}