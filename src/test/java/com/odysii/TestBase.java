package com.odysii;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;

public class TestBase {
    WebDriver driver;
    @AfterClass
    public void tearDown(){
        driver.quit();
    }
    protected void wait(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected boolean isElementExist(By by){
        boolean res = true;
        try{
            driver.findElement(by);
        }catch (NoSuchElementException e){
            res = false;
        }
        return res;
    }
    protected void fillMarketing(String promotionalText,String kewords,String screenshotFilePath,String appIconPath){
        driver.findElement(By.id("app-promotion")).sendKeys(promotionalText);
        driver.findElement(By.id("app-keywords")).sendKeys(kewords);
        driver.findElement(By.id("screenshotsFile")).sendKeys(screenshotFilePath);
        driver.findElement(By.id("iconFile")).sendKeys(appIconPath);
        wait(2000);
    }
}
