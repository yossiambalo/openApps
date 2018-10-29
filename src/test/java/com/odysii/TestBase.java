package com.odysii;


import com.odysii.selenium.page.util.DriverManager;
import com.odysii.selenium.page.util.DriverType;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TestBase {
    WebDriver driver;
    protected final int WAIT = 5000;
    protected final String cancelID = "cancel-button";
    protected final String backTxt = "BACK";
    protected final String continueTxt = "CONTINUE";
    protected final String finishTxt = "FINISH";
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

    @Parameters("browser")
    @BeforeClass
    public void init(String browser){
        switch (browser){
            case "chrome":
                driver = DriverManager.getWebDriver(DriverType.CHROME);
                break;
            case "edge":
                driver = DriverManager.getWebDriver(DriverType.IE);
                break;
            case "firefox":
                driver = DriverManager.getWebDriver(DriverType.FIREFOX);
                break;
            default:
        }
        driver.get("http://openappsqa.tveez.local:8080/openAppStore");
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
    protected void pageUpDown(boolean down){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (down) {
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        } else {
            robot.keyPress(KeyEvent.VK_PAGE_UP);
        }
        wait(3000);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }
}
