package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.amin.AdminPage;
import com.odysii.selenium.page.openApps.amin.SupportTicket;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.openApps.dev.summary.Summary;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SanityTest extends TestBase {
    private final static String DEV_USER_NAME = "user";
    private final static String DEV_USER_PASS = "123456";
    private final static String ADMIN_USER_NAME = "admin";
    private final static String ADMIN_USER_PASS = "admin";
    private final static String RETAILER_USER_NAME = "retailer";
    private final static String RETAILER_USER_PASS = "123456";
    private final static String APP_CLASS_NAME = "card";
    private final String zipFile = "TH.zip";
    RetailerHomePage retailerHomePage;
    User user;
    @BeforeMethod
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
    }
    //@Test
    public void _001_add_new_app_and_reject_no_fee(){
        int appListBeforeAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className(APP_CLASS_NAME));
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
        List<WebElement> actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        int actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        wait(WAIT);
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(),showUp.getStatus());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage)user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        SupportTicket supportTicket = adminPage.getSupportTickets();
        supportTicket.rejectNoFee();
        user.logout();
        //Valid rejected
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        wait(7000);
        showUp =  myApps.showUp(actualAppList.size()-1);
        wait(7000);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.REJECT.getStatus());
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(appListBeforeAdding,appListAfterAdding);
    }
    //@Test
    public void _002_add_new_app_and_reject_with_fee(){
        int appListBeforeAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className(APP_CLASS_NAME));
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
        List<WebElement> actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
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
        AdminPage adminPage = (AdminPage)user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        SupportTicket supportTicket = adminPage.getSupportTickets();
        supportTicket.rejectWithFee();
        user.logout();
        //Valid rejected
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        wait(7000);
        showUp =  myApps.showUp(actualAppList.size()-1);
        Assert.assertEquals(ApplicationStatus.REJECT.getStatus(),showUp.getStatus());
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(appListBeforeAdding,appListAfterAdding);
    }
    //@Test
    public void _003_add_new_app_and_certify(){
        int appListBeforeAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className(APP_CLASS_NAME));
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
        List<WebElement> actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        int actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        wait(3000);
        Summary summary = new Summary(driver);
        showUp.editApp(summary);
        wait(WAIT);
        showUp.certify();
        wait(4000);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(),showUp.getStatus());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage)user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        SupportTicket supportTicket = adminPage.getSupportTickets();
        supportTicket.approve();
        user.logout();
        //Valid certified
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        wait(7000);
        showUp =  myApps.showUp(actualAppList.size()-1);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.CERTIFIED.getStatus());
        showUp.addApplicationToStore();
        wait(4000);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.LIVE.getStatus());
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(appListBeforeAdding+1,appListAfterAdding);
    }
   // @Test
    public void _003_valid_add_new_version_to_application(){
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        showUp.getAppVersion();
        wait(WAIT);
        AppDetails appDetails = new AppDetails(driver);
        UploadCode uploadCode = appDetails.setUpAppDetails("1.0.7");
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.PRESUBMITTED.getStatus());
    }
    @Test
    public void _005_edit_version(){
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(0);
        showUp.edit();
        wait(WAIT);
        WebElement subtitleField = driver.findElement(By.id("appSubtitleSummary"));
        subtitleField.clear();
        subtitleField.sendKeys("QA test update of edit");
        pageUpDown(true);
        WebElement priceField = driver.findElement(By.id("appPrice"));
        priceField.clear();
        priceField.sendKeys("123");
        pageUpDown(true);
        WebElement finishbutton = driver.findElement(By.id("finishButton"));
        finishbutton.click();
        wait(WAIT);
        showUp.edit();
        wait(WAIT);
        subtitleField = driver.findElement(By.id("appSubtitleSummary"));
        String actualSubtitle = subtitleField.getAttribute("value");
        String expectedValue = "QA test update of edit";
        Assert.assertEquals(actualSubtitle,expectedValue, "Error message - failed to save the user's edit");
        WebElement pricing = driver.findElement(By.id("appPrice"));
        String actualPricing = pricing.getAttribute("value");
        String expectedValue2 = "123";
        Assert.assertEquals(actualPricing,expectedValue2, "Error message - failed to save the user's edit");


    }
    @AfterMethod
    public void afterMethod(){
        user.logout();
    }
}
