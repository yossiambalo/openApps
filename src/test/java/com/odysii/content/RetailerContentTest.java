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

        String expected = "App Store";
        retailerHomePage.getAppStore();
        String actualTxt = driver.findElement(By.xpath("//h1[contains(text(), 'App Store')]")).getText();
        Assert.assertEquals(expected,actualTxt);

    }



}
