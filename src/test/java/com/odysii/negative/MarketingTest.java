package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.Availabilty;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.util.FieldType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class MarketingTest extends TestBase {
    private ShowUp showUp;
    private final String zipFile = "TH.zip";
    DevHomePage devUser;

    @BeforeClass

    public void prepare(){
        prepareTest("app_details_DevContent_PreSubmitted.properties",ApplicationStatus.PRESUBMITTED);
        category = "AppVersionsTest";
    }


    @Test // Test will failed until fix of space counts as a valid input!
    public void _001_promotional_text_is_empty_negative(){
//        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(0);
        String curVer = driver.findElement(By.className("text-medium-title")).getText().split(" ")[1].split("\\.")[0];
        int newVerNum = Integer.parseInt(curVer)+1;
        String newVer = String.valueOf(newVerNum);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        Dependencies dependencies = appDetails.setUpAppDetails(newVer + ".8.8");
        wait(3000);
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        driver.findElement(By.id("app-promotion")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("app-promotion")).sendKeys(Keys.DELETE);
        wait(WAIT);
        driver.findElement(By.id("finishButton")).click();
        wait(WAIT);
        String actualValue = driver.findElements(By.id("new-application-success-error-message")).get(1).getText().trim();
        String expectedValue = "Some required fields are missing or incorrect";
        Assert.assertEquals(actualValue, expectedValue);
        driver.findElement(By.id("cancelButton")).click();
        showUp.backToMyApps();

    }

    @Test
    public void _002_keywords_is_empty_negative(){
//        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(0);
        String curVer = driver.findElement(By.className("text-medium-title")).getText().split(" ")[1].split("\\.")[0];
        int newVerNum = Integer.parseInt(curVer)+1;
        String newVer = String.valueOf(newVerNum);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        wait(3000);
        Dependencies dependencies = appDetails.setUpAppDetails(newVer + ".8.8");
        wait(WAIT);
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        driver.findElement(By.id("app-keywords")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("app-keywords")).sendKeys(Keys.DELETE);
        wait(WAIT);
        driver.findElement(By.id("finishButton")).click();
        wait(WAIT);
        String actualValue = driver.findElements(By.id("new-application-success-error-message")).get(1).getText().trim();
        String expectedValue = "Some required fields are missing or incorrect";
        Assert.assertEquals(actualValue, expectedValue);
        driver.findElement(By.id("cancelButton")).click();
        showUp.backToMyApps();

    }

    @Test
    public void _003_add_new_screenshot_positive(){
//        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(0);
        String curVer = driver.findElement(By.className("text-medium-title")).getText().split(" ")[1].split("\\.")[0];
        int newVerNum = Integer.parseInt(curVer)+1;
        String newVer = String.valueOf(newVerNum);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        Dependencies dependencies = appDetails.setUpAppDetails(newVer + ".8.8");
        wait(3000);
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        wait(WAIT);
        marketing.fillMarketing("Promotion Text","Key Words","800x400.png",null,false);
        wait(WAIT);
        String actualValue = driver.findElements(By.id("new-application-success-error-message")).get(1).getText().trim();
        Assert.assertEquals(actualValue,"Saved screenshots successfully");
        driver.findElement(By.id("cancelButton")).click();

    }

}
