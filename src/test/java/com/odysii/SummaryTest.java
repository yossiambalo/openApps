package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.myApps.Login;
import com.odysii.selenium.page.myApps.MyApps;
import com.odysii.selenium.page.myApps.summary.Marketing;
import com.odysii.selenium.page.myApps.summary.ShowUp;
import com.odysii.selenium.page.myApps.summary.Summary;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SummaryTest extends TestBase {

    HomePage homePage;
    @BeforeClass
    public void login() {
        Login login = new  Login(driver);
        login.login("user", "123456",false);
    }

    @Test
    public void _001_name_is_empty_summary_negative() {
        MyApps myApps = homePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        Summary summary = showUp.getSummary();
        summary.editSummary(FieldType.APPNAME, "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validation on name's field - it's empty!");
    }

    @Test
    public void _002_subtitle_is_empty_negative(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(1);
        Summary summary = showUp.getSummary();
        summary.editSummary(FieldType.SUBTITLE, "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validation on subtitle's field - it's empty!");
    }

  /* @Ignore
    public void _003_language_is_empty_negative(){
        MyApps myApps = homePage.getMyAppsPage(driver);
        Summary summary = myApps.showUp(2);
        WebElement dropDownLanguage = driver.findElement(By.id("app-language"));
        dropDownLanguage.click();

        // summary.editSummary(FieldType.LANGUAGE, "");
        WebElement finishButton = driver.findElement(By.id("finish-button"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validation on language's field - it's empty!");
    }

    @Ignore
    public void _004_category_is_empty_negative(){

        MyApps myApps = homePage.getMyAppsPage(driver);
        Summary summary = myApps.showUp(1);
        summary.editSummary(FieldType.CATEGORY, "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validation on category's field - it's empty!");
    }

    @Ignore
    public void _005_availavility_is_empty_negative(){

        MyApps myApps = homePage.getMyAppsPage(driver);
        Summary summary = myApps.showUp(1);
        summary.editSummary(FieldType.AVAILABILITY, "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validation on availability's field - it's empty!");
    }

    @Ignore
    public void _006_retailers_is_empty_negative(){

        MyApps myApps = homePage.getMyAppsPage(driver);
        Summary summary = myApps.showUp(0);
        summary.editSummary(FieldType.RETAILERS, "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validation on retailers's field - it's empty!");
    }

    @Ignore
    public void _007_two_mandatory_fields_is_empty_negative(){

        MyApps myApps = homePage.getMyAppsPage(driver);
        Summary summary = myApps.showUp(0);
        summary.editSummary(FieldType.RETAILERS, "");
        summary.editSummary(FieldType.APPNAME, "");
        WebElement finishButton = driver.findElement(By.xpath("//*[contains(text(), '"+ finishTxt +"')]"));
        finishButton.click();
        Boolean actualValue = isElementExist(By.xpath("//*[contains(text(), 'ADD NEW APP')]"));
        Assert.assertFalse(actualValue, "There's no validations on retailers & name fields - they both empty!");
    }
*/
  @Test
  public void _008_certify_app_positive(){
      MyApps myApps = homePage.getMyAppsPage(driver);
      ShowUp showUp = myApps.showUp(1);
      Summary summary = showUp.getSummary();


  }
}
