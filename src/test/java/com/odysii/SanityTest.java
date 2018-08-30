package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.amin.AdminPage;
import com.odysii.selenium.page.openApps.amin.SupportTicket;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SanityTest extends TestBase {
    private final String zipFile = "TH.zip";
    RetailerHomePage retailerHomePage;
    User user;
   @BeforeMethod
   public void login(){
       user = new User(driver);
       retailerHomePage = (RetailerHomePage) user.login("retailer","123456",UserType.RETAILER);
   }
    @Test
    public void _001_add_new_app_and_certify(){
        int appListBeforeAdding = driver.findElements(By.className("card")).size();
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className("card"));
        int appsSize = appsList.size();
        int expectedValue = appsSize+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppDetails();
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        List<WebElement> actualAppList = driver.findElements(By.className("card"));
        int actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        wait(3000);
        showUp.certify();
        wait(4000);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(),showUp.getStatus());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage)user.login("admin","admin",UserType.ADMIN);
        SupportTicket supportTicket = adminPage.getSupportTickets();
        supportTicket.approve();
        user.logout();
        //Valid certified
        devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className("card"));
        showUp =  myApps.showUp(actualAppList.size()-1);
        Assert.assertEquals(ApplicationStatus.CERTIFIED.getStatus(),showUp.getStatus());
        showUp.addApplicationToStore();
        wait(2000);
        Assert.assertEquals(ApplicationStatus.LIVE.getStatus(),showUp.getStatus());
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login("retailer","123456",UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className("card")).size();
        Assert.assertEquals(appListBeforeAdding+1,appListAfterAdding);
    }
    @Test
    public void _002_add_new_app_and_reject_no_fee(){
        int appListBeforeAdding = driver.findElements(By.className("card")).size();
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className("card"));
        int appsSize = appsList.size();
        int expectedValue = appsSize+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppDetails();
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        List<WebElement> actualAppList = driver.findElements(By.className("card"));
        int actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        wait(3000);
        showUp.certify();
        wait(4000);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(),showUp.getStatus());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage)user.login("admin","admin",UserType.ADMIN);
        SupportTicket supportTicket = adminPage.getSupportTickets();
        supportTicket.rejectNoFee();
        user.logout();
        //Valid rejected
        devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className("card"));
        showUp =  myApps.showUp(actualAppList.size()-1);
        Assert.assertEquals(ApplicationStatus.REJECT.getStatus(),showUp.getStatus());
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login("retailer","123456",UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className("card")).size();
        Assert.assertEquals(appListBeforeAdding,appListAfterAdding);
    }
    @Test
    public void _003_add_new_app_and_reject_with_fee(){
        int appListBeforeAdding = driver.findElements(By.className("card")).size();
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className("card"));
        int appsSize = appsList.size();
        int expectedValue = appsSize+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppDetails();
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        List<WebElement> actualAppList = driver.findElements(By.className("card"));
        int actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        wait(3000);
        showUp.certify();
        wait(4000);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(),showUp.getStatus());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage)user.login("admin","admin",UserType.ADMIN);
        SupportTicket supportTicket = adminPage.getSupportTickets();
        supportTicket.rejectWithFee();
        user.logout();
        //Valid rejected
        devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className("card"));
        showUp =  myApps.showUp(actualAppList.size()-1);
        Assert.assertEquals(ApplicationStatus.REJECT.getStatus(),showUp.getStatus());
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login("retailer","123456",UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className("card")).size();
        Assert.assertEquals(appListBeforeAdding,appListAfterAdding);
    }
    @AfterMethod
    public void afterMethod(){
       user.logout();
    }
}
