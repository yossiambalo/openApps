package com.odysii;

import org.openqa.selenium.WebDriver;
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
}
