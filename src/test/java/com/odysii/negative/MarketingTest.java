package com.odysii.negative;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.summary.Marketing;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.util.FieldType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarketingTest extends TestBase {
    private ShowUp showUp;

    User user;
    DevHomePage devUser;
    @BeforeClass
    public void login(){
        user = new User(driver);
        devUser = (DevHomePage) user.login("user","123456", UserType.DEVELOPER);
    }
    @Test // Test will failed until fix of back space counts as a valid input!
    public void _001_promotional_text_is_empty_negative(){

        MyApps myApps = devUser.getMyAppsPage(driver);
        showUp = myApps.showUp(4);
        showUp.getAppVersion();
        WebElement version = driver.findElement(By.id("appVersion"));
        version.sendKeys("1.9.3");
        WebElement continueButton = driver.findElement(By.id("nextButton"));
        scrollDown(continueButton);
        continueButton.click();
        WebElement agreeAndUpload = driver.findElement(By.id("codeFile"));
        wait(WAIT);
        agreeAndUpload.sendKeys("C:\\Git_repository\\openApps\\src\\main\\resources\\application\\barcodeApp_1541669891338.zip");
        WebElement continueButtonInUploadPage = driver.findElement(By.id("nextButton"));
        continueButtonInUploadPage.click();
        wait(WAIT);
        WebElement promotionalText = driver.findElement(By.id("app-promotion"));
        promotionalText.clear();
        WebElement finishButton = driver.findElement(By.id("finishButton"));
        scrollDown(finishButton);
        finishButton.click();
        wait(WAIT);
        Boolean actualValue = isElementExist(By.id("new-application-success-error-message"));
        Assert.assertEquals(actualValue,"Some required fields are missing");

    }

    @Test // Test will failed until fix of back space counts as a valid input!
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
