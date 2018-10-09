package com.odysii;

import com.odysii.selenium.page.openApps.dev.AppVersion;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.util.FieldType;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase{
    private final String zipFile = "TH.zip";
    DevHomePage devUser;
    AppVersion appVersion;
    @BeforeClass
    public void login(){
        devUser = new DevHomePage(driver);
        devUser.login("user","123456");
    }

    @Test
    public void _001_version_field_without_value_positive(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        appVersion = showUp.getAppVersions();
        appVersion.clickNewVersion();
        wait(WAIT);
        pageUpDown(true);
        wait(WAIT);
        //WebElement continueBtn =  driver.findElement(By.id("nextButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("nextButton")));
        Boolean actualValue = isElementExist(By.id("appSubtitle"));
        Assert.assertTrue(actualValue,"QA failed");

    }

    @Test
    public void _002_version_field_with_value_positive(){
        appVersion.editAppVersion(FieldType.NEW_VERSION_NUMBER, "2.2.2");
        pageUpDown(true);
        wait(WAIT);
        WebElement continueBtn =  driver.findElement(By.id("nextButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
        Boolean actualValue = isElementExist(By.id("newAppUploadCode"));
        Assert.assertTrue(actualValue, "QA failed");

    }

    @Test
    public void _003_continue_without_upload_code_positive(){
        WebElement continueBtn =  driver.findElement(By.id("nextButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
        Boolean actualValue = isElementExist(By.id("newAppUploadCode"));
        Assert.assertTrue(actualValue, "QA failed");
    }

    @Test
    public void _004_continue_after_upload_code_NOT_zip_format_positive(){

        appVersion.editAppVersion(FieldType.SELECT_UPLOAD, "dog2.jpg");
        WebElement continueBtn =  driver.findElement(By.id("nextButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
        Boolean actualValue = isElementExist(By.id("codeFile"));
        Assert.assertTrue(actualValue, "QA failed");
    }

    @Test
    public void _005_continue_after_upload_code_zip_format_positive(){
        appVersion.editAppVersion(FieldType.SELECT_UPLOAD, "TH.zip");
        WebElement continueBtn =  driver.findElement(By.id("nextButton"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continueBtn);
        Boolean actualValue = isElementExist(By.id("app-promotion"));
        Assert.assertTrue(actualValue, "QA failed");
        wait(WAIT);
        pageUpDown(true);
        wait(WAIT);

    }

    @Test
    public void _006_marketing_continue_without_change(){
        pageUpDown(true);
        wait(WAIT);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("nextButton")));
        Boolean actualValue = isElementExist(By.id("editAppNewVersion"));
        Assert.assertTrue(actualValue, "QA failed");
    }

    @Test
    public void _007_marketing_add_screenshot_save(){

    }

}