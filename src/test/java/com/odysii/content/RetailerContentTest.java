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



    @BeforeClass
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        category = "Retailer Content";
    }
    @Test
    public void _001_valid_text_app_store(){
        String expected = "APP STORE";
        String expectedHeader = "App Store";
        String expectedSearch = "Search";
        retailerHomePage.getAppStore();
        String actualTxt = driver.findElement(By.xpath("//span[contains(text(), 'App Store')]")).getText();
        String actualHeader = driver.findElement(By.className("h2")).getText();
        String actualSearch = driver.findElement(By.id("applicationSearchInput")).getAttribute("placeholder");
        Assert.assertEquals(actualTxt,expected);
        Assert.assertEquals(actualHeader,expectedHeader);
        Assert.assertEquals(actualSearch,expectedSearch);



    }

    @Test
    public void _002_valid_text_app_library(){
        String expected = "LIBRARY";
        String expectedHeader = "App Library";
        String expectedSearch = "Search";
        retailerHomePage.getAppLibrary();
        String actualTxt = driver.findElement(By.id("navItem13")).getText();
        String actualHeader = driver.findElement(By.className("h2")).getText();
        String actualSearch = driver.findElement(By.id("libraryApplicationSearchInput")).getAttribute("placeholder");
        Assert.assertEquals(actualTxt, expected);
        Assert.assertEquals(actualHeader,expectedHeader);
        Assert.assertEquals(actualSearch,expectedSearch);
    }

    @Test
    public void _003_valid_text_campaigns() {
        String expected = "CAMPAIGNS";
        String expectedHeader = "Campaigns";
        String expectedSearch = "Search";
        retailerHomePage.getCampaign();
        String actualTxt = driver.findElement(By.id("navItem14")).getText();
        String actualHeader = driver.findElement(By.className("h2")).getText();
        String actualSearch = driver.findElement(By.id("campaignSearchInput")).getAttribute("placeholder");
        Assert.assertEquals(expected, actualTxt);
        Assert.assertEquals(actualHeader,expectedHeader);
        Assert.assertEquals(actualSearch,expectedSearch);
    }
    @Test
    public void _004_header_valid_text_scheduling(){
        String expected = "SCHEDULING";
        String expectedHeader = "Scheduling";
        String expectedSearch = "Search";
        retailerHomePage.getScheduling();
        String actualTxt = driver.findElement(By.id("navItem15")).getText();
        String actualHeader = driver.findElement(By.className("h2")).getText();
        String actualSearch = driver.findElement(By.id("schedulingCampaignSearchInput")).getAttribute("placeholder");
        Assert.assertEquals(expected,actualTxt);
        Assert.assertEquals(actualHeader,expectedHeader);
        Assert.assertEquals(actualSearch,expectedSearch);
    }

    @Test
    public void _005_header_valid_text_keymanagement(){
        String expected = "KEY MANAGEMENT";
        String expectedHeader = "MANAGE KEYS";
        retailerHomePage.getKeysMGMT();
        String actualTxt = driver.findElement(By.id("navItem16")).getText();
        String actualHeader = driver.findElement(By.className("h2")).getText();
        Assert.assertEquals(expected,actualTxt);
        Assert.assertEquals(actualHeader,expectedHeader);
    }




}
