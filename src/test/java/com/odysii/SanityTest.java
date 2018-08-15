package com.odysii;

import com.odysii.selenium.page.myApps.*;
import com.odysii.selenium.page.myApps.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SanityTest extends TestBase {
    private User user;
    private final String zipFile = "TH.zip";
    @BeforeClass
    public void login() {
        user = new User(driver);
        user.login("user", "123456",false);
    }

    @Test
    public void _001_add_new_app(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className("card"));
        int appsSize = appsList.size();
        ShowUp showUp = myApps.showUp(appsList.get(appsSize-1));
        showUp.certify();
        user.logout();
        int expectedValue = appsSize+1;
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        wait(WAIT);
        UploadCode uploadCode = appDetails.setUpAppDetails();
        wait(WAIT);
        Marketing marketing = uploadCode.upload(zipFile);
        wait(WAIT);
        marketing.fillMarketing();
        wait(WAIT);
        int actualValue = driver.findElements(By.className("card")).size();
        Assert.assertEquals(expectedValue,actualValue,"Expected value not equals to actual value");
    }
}
