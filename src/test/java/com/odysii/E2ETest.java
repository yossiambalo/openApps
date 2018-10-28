package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.SupportTicket;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.openApps.dev.summary.Summary;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class E2ETest extends TestBase {
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
    MyApps myApps;
    List<WebElement> actualAppList;
    int actualValue;
    DevHomePage devUser;
    int appListBeforeAdding;
    @BeforeClass
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
    }
    @Test(priority = 1)
    public void _001_valid_add_new_app(){
        //get number of live apps from retailer page
        appListBeforeAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className(APP_CLASS_NAME));
        int appsSize = appsList.size();
        int expectedValue = appsSize+1;
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetails();
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        Assert.assertTrue(myApps.getTitle(actualValue-1).toLowerCase().contains(appDetails.getAppTitle().toLowerCase()));
        Assert.assertTrue(myApps.getDescription(actualValue-1).toLowerCase().contains(appDetails.getAppDescription().toLowerCase()));
    }
    @Test(priority = 2, dependsOnMethods = "_001_valid_add_new_app")
    public void _002_valid_app_reject_no_fee(){
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        showUp.certify();
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
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        showUp =  myApps.showUp(actualAppList.size()-1);
        wait(7000);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.REJECT.getStatus());
        showUp.backToMyApps();
    }
    @Test(priority = 3, dependsOnMethods = "_002_valid_app_reject_no_fee")
    public void _003_valid_reject_with_fee(){
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        showUp.certify();
        wait(WAIT);
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
        showUp.backToMyApps();
    }
    @Test(priority = 4, dependsOnMethods = "_003_valid_reject_with_fee")
    public void _004_edit_and_certify_and_go_live(){
        myApps = devUser.getMyAppsPage(driver);
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        Summary summary = new Summary(driver);
        showUp.editApp(summary);
        showUp.certify();
        wait(WAIT);
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
        wait(8000);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.LIVE.getStatus());
    }
    @Test(priority = 5, dependsOnMethods = "_004_edit_and_certify_and_go_live")
    public void _005_valid_app_add_to_app_store(){
        user.logout();
        //Valid app added to retailer store
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        int appListAfterAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(appListBeforeAdding + 1,appListAfterAdding);
    }
    @Test(priority = 6)
    public void _006_valid_add_new_version_to_application(){
        user.logout();
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        UploadCode uploadCode = appDetails.setUpAppDetails("1.0.7");
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing();
        wait(7000);
        Assert.assertEquals(showUp.getStatus(),ApplicationStatus.PRESUBMITTED.getStatus());
    }
}
