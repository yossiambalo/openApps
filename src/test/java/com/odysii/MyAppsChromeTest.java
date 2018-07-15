package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.*;
import com.odysii.selenium.page.myApps.AppDetails;
import com.odysii.selenium.page.myApps.Marketing;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.UploadCode;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyAppsChromeTest extends TestBase{

    private final int WAIT = 2000;
    @BeforeClass
    public void init(){
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
    }
    @Test
    public void _001_add_new_app(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        int expectedValue = driver.findElements(By.className("card")).size()+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppDetails();
        wait(WAIT);
        Marketing marketing = uploadCode.upload();
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        int actualValue = driver.findElements(By.className("card")).size();
        Assert.assertEquals(expectedValue,actualValue,"Expected value not equals to actual value");
    }
}
