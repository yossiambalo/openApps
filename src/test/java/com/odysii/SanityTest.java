package com.odysii;

import com.odysii.selenium.page.openApps.dev.AppDetails;
import com.odysii.selenium.page.openApps.dev.Marketing;
import com.odysii.selenium.page.openApps.dev.MyApps;
import com.odysii.selenium.page.openApps.dev.UploadCode;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SanityTest extends TestBase {
    private final String zipFile = "TH.zip";
    @Test
    public void _001_add_new_app(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className("card"));
        int appsSize = appsList.size();
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
        List<WebElement> actualAppList = driver.findElements(By.className("card"));
        int actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
        showUp.certify();
        user.logout();
        //AdminPage adminPage = user.login("admin","admin", true);
    }
}
