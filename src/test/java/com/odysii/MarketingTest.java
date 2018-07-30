package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.summary.Marketing;
import com.odysii.selenium.page.myApps.summary.ShowUp;
import com.odysii.selenium.page.myApps.summary.Summary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarketingTest extends TestBase {
    private final int WAIT = 2000;
    private final String cancelTxt = "CANCEL";
    private final String backTxt = "BACK";
    private final String continueTxt = "CONTINUE";
    private final String finishTxt = "FINISH";

    @BeforeClass
    public void init() {
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");

    }
    @Test
    public void _001_promotional_token_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        Marketing marketing = showUp.getMarketing();
        marketing.editMarketing(FieldType.PROMOTIONAL_TOKEN,"");
        WebElement finishButton = driver.findElement(By.id("finish-button"));
        finishButton.click();
        Boolean actualValue =isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "No validation on empty field (promotion token ) - saved with empty value");
    }

    @Test
    public void _002_keywords_is_empty_negative(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        Marketing marketing = showUp.getMarketing();
        marketing.editMarketing(FieldType.KEYWORDS,"");
        WebElement finishButton = driver.findElement(By.id("finish-button"));
        finishButton.click();
        Boolean actualValue =isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "No validation on empty field (keyword) - saved with empty value");
    }
    // Todo - add test to upload screenshot's button (waiting for ID)
    //ToDo: add test to upload appicon's button (waiting for ID)
}
