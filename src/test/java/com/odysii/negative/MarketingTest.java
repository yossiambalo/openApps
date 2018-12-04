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

public class MarketingTest extends TestBase {
    private ShowUp showUp;
    private final String zipFile = "TH.zip";

    User user;
    DevHomePage devUser;
    final String CATEGORYTEST = "Marketing Tests";
    @BeforeClass
    public void login(){
        user = new User(driver);
        devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);

    }
    @Test // Test will failed until fix of space counts as a valid input!
    public void _001_promotional_text_is_empty_negative(){
        logger = extent.startTest("_001_promotional_text_is_empty_negative").assignCategory(CATEGORYTEST);
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(4);
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
        String actualValue =driver.findElement(By.id("new-application-success-error-message")).getText().trim();
        Assert.assertEquals(actualValue,"Some required fields are missing");


//        WebElement version = driver.findElement(By.id("appVersion"));
//        version.sendKeys("1.9.5");
//        WebElement continueButton = driver.findElement(By.id("nextButton"));
//        scrollDown(continueButton);
//        continueButton.click();
//        WebElement agreeAndUpload = driver.findElement(By.id("codeFile"));
//        wait(WAIT);
//        agreeAndUpload.sendKeys("C:\\Git_repository\\openApps\\src\\main\\resources\\application\\barcodeApp_1541669891338.zip");
//        WebElement continueButtonInUploadPage = driver.findElement(By.id("nextButton"));
//        continueButtonInUploadPage.click();
//        WebElement promotionalText = driver.findElement(By.id("app-promotion"));
//        promotionalText.clear();
//        WebElement finishButton = driver.findElement(By.id("finishButton"));
//        scrollDown(finishButton);
//        finishButton.click();

    }

    //@Test // Test will failed until fix of back space counts as a valid input!
    public void _002_keywords_is_empty_negative(){
        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(3);
        showUp.getAppVersion();
        WebElement version = driver.findElement(By.id("appVersion"));
        version.sendKeys("1.5.7");
        wait(WAIT);
        WebElement continueButton = driver.findElement(By.id("nextButton"));
        scrollDown(continueButton);
        continueButton.click();
        WebElement agreeAndUpload = driver.findElement(By.id("codeFile"));
        wait(WAIT);
        agreeAndUpload.sendKeys("C:\\Users\\roi.avital\\Desktop\\Zip applications for tests\\appConfig_1541669841907.zip");
        WebElement continueButtonInUploadPage = driver.findElement(By.id("nextButton"));
        continueButtonInUploadPage.click();
        wait(WAIT);
        WebElement keywords = driver.findElement(By.id("app-keywords"));
        keywords.clear();
        WebElement finishButton = driver.findElement(By.id("finishButton"));
        scrollDown(finishButton);
        finishButton.click();
        wait(WAIT);
        Boolean actualValue = isElementExist(By.id("new-application-success-error-message"));
        Assert.assertEquals(actualValue,"Some required fields are missing");
    }

//    @Test
//    public void _003_add_new_screenshot_positive(){
//        if (!driver.getCurrentUrl().contains("marketing")){
//            MyApps myApps = devUser.getMyAppsPage(driver);
//            showUp = myApps.showUp(1);
//        }
//        Marketing marketing = showUp.getMarketing();
//        marketing.editMarketing(FieldType.SCREEN_SHOTS_FILE,"C:\\yossi\\dog3.jpg");
//
//    }

    //ToDo: add test to upload appicon's button (waiting for ID)
}
