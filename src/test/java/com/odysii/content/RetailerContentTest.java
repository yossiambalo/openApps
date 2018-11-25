package com.odysii.content;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetailerContentTest extends TestBase {
    RetailerHomePage retailerHomePage;
    User user;
    @BeforeClass
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login("retailer","123456",UserType.RETAILER);

    }
    @Test
    public void _001_header_valid_text_app_store(){
        logger = extent.startTest("_001_header_valid_text_app_store");
        String expected = "APP STORE";
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'App Store')]")).getText();
        Assert.assertEquals(expected,actualTxt);
        retailerHomePage.getAppStore();


    }

    @Test
    public void _002_header_valid_text_app_library(){
        logger = extent.startTest("_002_header_valid_text_app_library");
        String expected = "APP LIBRARY";
        retailerHomePage.getAppLibrary();
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'App Library')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }

    @Test
    public void _003_header_valid_text_campaigns() {
        logger = extent.startTest("_003_header_valid_text_campaigns");
        String expected = "CAMPAIGNS";
        retailerHomePage.getCampaigs();
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'Campaigns')]")).getText();
        Assert.assertEquals(expected, actualTxt);

    }
        @Test
        public void _004_header_valid_text_scheduling(){
            logger = extent.startTest("_004_header_valid_text_scheduling");
            String expected = "SCHEDULING";
            retailerHomePage.getScheduling();
            String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'Scheduling')]")).getText();
            Assert.assertEquals(expected,actualTxt);

    }

    @Test
    public void _005_header_valid_text_keymanagement(){
        logger = extent.startTest("_005_header_valid_text_keymanagement");
        String expected = "KEY MANAGEMENT";
        retailerHomePage.getKeysMGMT();
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'Key Management')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }





}
