package com.odysii;

import com.odysii.selenium.page.myApps.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SanityTest extends TestBase {
    private final String zipFile = "dog2.jpg";
    @BeforeClass
    public void login() {
        Login login = new  Login(driver);
        login.login("user", "123456",false);
    }

    @Test
    public void _001_add_new_app(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        int expectedValue = driver.findElements(By.className("card")).size()+1;
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
