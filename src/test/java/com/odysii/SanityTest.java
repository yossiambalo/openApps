package com.odysii;

import com.odysii.selenium.page.openApps.amin.AdminPage;
import com.odysii.selenium.page.openApps.amin.SupportTicket;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SanityTest extends TestBase {
    private final String zipFile = "TH.zip";
    DevHomePage devUser;
   @BeforeClass
   public void login(){
       devUser = new DevHomePage(driver);
       devUser.login("user","123456");
   }
    @Test
    public void _001_add_new_app(){
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
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        showUp.certify();
//        devUser.logout();
//        AdminPage adminPage = new AdminPage(driver);
//        adminPage.login("admin","admin");
//        SupportTicket supportTicket = adminPage.getSupportTickets();
//        supportTicket.approve();
        //AdminPage adminPage = user.login("admin","admin", true);
    }
}
