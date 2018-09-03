package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.retailer.AppLibrary;
import com.odysii.selenium.page.openApps.retailer.AppStore;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetailerTest extends TestBase {
    private final static String APP_CLASS_NAME = "card";
    private final static String RETAILER_USER_NAME = "retailer";
    private final static String RETAILER_USER_PASS = "123456";
    RetailerHomePage retailerHomePage;
    User user;
    @BeforeClass
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS, UserType.RETAILER);
    }
    @Test
    public void _001_add_and_remove_app_library(){
        retailerHomePage.getAppLibrary();
        int expectedApp = driver.findElements(By.className(APP_CLASS_NAME)).size() + 1;
        AppStore appStore = retailerHomePage.getAppStore();
        appStore.addAppToLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        AppLibrary appLibrary = retailerHomePage.getAppLibrary();
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"Failed to adding application to library!");
        appLibrary.removeAppFromLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        Assert.assertEquals(actualApps - 1,driver.findElements(By.className(APP_CLASS_NAME)).size());
    }
}
