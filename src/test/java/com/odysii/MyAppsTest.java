package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
;import java.util.List;

public class MyAppsTest extends TestBase{

    private final int WAIT = 2000;
    @BeforeClass
    public void init(){
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
//        wait(WAIT);
//        homePage.getDashboardPage(driver);
//        wait(WAIT);
//        homePage.getPublicProfilePage(driver);
//        wait(WAIT);
//        homePage.getRevenueReportPage(driver);
//        wait(WAIT);
//        homePage.getSupportTicketstPage(driver);
//        wait(WAIT);
//        homePage.getTrasactionHistoryPage(driver);
//        wait(WAIT);
    }
    @Test
    public void _001_add_new_app(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        int expectedValue = driver.findElements(By.className("card")).size()+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppdetails();
        Marketing marketing = uploadCode.upload();
        wait(WAIT);
        marketing.fillMarketing();
        int actualValue = driver.findElements(By.className("card")).size();
        Assert.assertEquals(expectedValue,actualValue,"Expected value not equals to actual value");
    }
}
