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
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyAppsE2ETest extends TestBase{

    private final int WAIT = 2000;
    @Parameters("browser")
    @BeforeClass
    //ToDo: export method to TestBase

    public void init(String browser){
        switch (browser){
            case "chrome":
                driver = DriverManager.getWebDriver(DriverType.CHROME);
                break;
            case "ie":
                driver = DriverManager.getWebDriver(DriverType.IE);
                break;
            case "firefox":
                driver = DriverManager.getWebDriver(DriverType.FIREFOX);
                break;
                default:
        }
        driver.get("http://openapps.tveez.local:8080/openAppStore");
    }
    @Test
    public void _001_add_new_app(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        driver.findElement(By.xpath("//*[contains(text(), 'Automation App')]")).click();
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
    @Test
    public void _002_valid_app_summery(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        myApps.showUp(1);
        System.out.println();
    }
}
