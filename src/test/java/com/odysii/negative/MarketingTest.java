package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.*;
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
    public static String DEV_USER_NAME = "auto.open.apps@gmail.com";
    DevHomePage devUser;
    @BeforeClass
    public void login(){
        user = new User(driver);
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        category = "Marketing";

    }
    @Test // Test will failed until fix of space counts as a valid input!
    public void _001_promotional_text_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(2);
        String curVer = driver.findElement(By.className("text-medium-title")).getText().split(" ")[1].split("\\.")[0];
        int newVerNum = Integer.parseInt(curVer)+1;
        String newVer = String.valueOf(newVerNum);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        UploadCode uploadCode = appDetails.setUpAppDetails(newVer + ".8.8");
        Marketing marketing = uploadCode.upload(zipFile);
        driver.findElement(By.id("app-promotion")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("app-promotion")).sendKeys(Keys.DELETE);
        wait(WAIT);
        driver.findElement(By.id("finishButton")).click();
        wait(WAIT);
        String actualValue = driver.findElement(By.id("new-application-success-error-message")).getText().trim();
        Assert.assertEquals(actualValue,"Some required fields are missing");
        driver.findElement(By.id("cancelButton")).click();

    }

    @Test
    public void _002_keywords_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(2);
        String curVer = driver.findElement(By.className("text-medium-title")).getText().split(" ")[1].split("\\.")[0];
        int newVerNum = Integer.parseInt(curVer)+1;
        String newVer = String.valueOf(newVerNum);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        UploadCode uploadCode = appDetails.setUpAppDetails(newVer + ".8.8");
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        driver.findElement(By.id("app-keywords")).sendKeys(Keys.CONTROL + "a");
        driver.findElement(By.id("app-keywords")).sendKeys(Keys.DELETE);
        wait(WAIT);
        driver.findElement(By.id("finishButton")).click();
        wait(WAIT);
        String actualValue = driver.findElement(By.id("new-application-success-error-message")).getText().trim();
        Assert.assertEquals(actualValue,"Some required fields are missing");
        driver.findElement(By.id("cancelButton")).click();

    }

    @Test
    public void _003_add_new_screenshot_positive(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(2);
        String curVer = driver.findElement(By.className("text-medium-title")).getText().split(" ")[1].split("\\.")[0];
        int newVerNum = Integer.parseInt(curVer)+1;
        String newVer = String.valueOf(newVerNum);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        UploadCode uploadCode = appDetails.setUpAppDetails(newVer + ".8.8");
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing("Promotion Text","Key Words","bike.jpg",null,false);
        wait(WAIT);
        String actualValue = driver.findElement(By.id("new-application-success-error-message")).getText().trim();
        Assert.assertEquals(actualValue,"Screenshots saved successfully");

    }

}
