package com.odysii.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    public static WebDriver getWebDriver(DriverType driverType){
        String driverPath = "src"+ File.separator+"main"+File.separator+"resources"+File.separator+"driver"+File.separator;
        WebDriver driver = null;
        switch (driverType){
            case CHROME:
                System.setProperty("webdriver.chrome.driver", driverPath+"chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case IE:
                //add logic
                break;
            case OPERA:
                //add logic
            case FIREFOX:
                //add logic
                default:
                    return null;
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
