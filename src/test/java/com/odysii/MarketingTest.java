package com.odysii;

import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.Login;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.summary.Marketing;
import com.odysii.selenium.page.myApps.summary.ShowUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MarketingTest extends TestBase {
    HomePage homePage;
    private ShowUp showUp;
    @BeforeClass
    public void login() {
        Login login = new  Login(driver);
        login.login("user", "123456",false);
    }

    @Test
    public void _001_promotional_token_is_empty_negative(){

        MyApps myApps = homePage.getMyAppsPage(driver);
        showUp = myApps.showUp(1);
        Marketing marketing = showUp.getMarketing();
        marketing.editMarketing(FieldType.PROMOTIONAL_TOKEN,"");
        WebElement finishButton = driver.findElement(By.id("finish-button"));
        finishButton.click();
        Boolean actualValue =isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "No validation on empty field (promotion token ) - saved with empty value");
    }

    @Test
    public void _002_keywords_is_empty_negative(){
        if (!driver.getCurrentUrl().contains("marketing")){
            MyApps myApps = homePage.getMyAppsPage(driver);
            showUp = myApps.showUp(1);
        }
        Marketing marketing = showUp.getMarketing();
        marketing.editMarketing(FieldType.KEYWORDS,"");
        WebElement finishButton = driver.findElement(By.id("finish-button"));
        finishButton.click();
    }

    @Test
    public void _003_add_new_screenshot_positive(){
        if (!driver.getCurrentUrl().contains("marketing")){
            MyApps myApps = homePage.getMyAppsPage(driver);
            showUp = myApps.showUp(1);
        }
        Marketing marketing = showUp.getMarketing();
        marketing.editMarketing(FieldType.SCREEN_SHOTS_FILE,"C:\\yossi\\dog3.jpg");

    }

    //ToDo: add test to upload appicon's button (waiting for ID)
}
