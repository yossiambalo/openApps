package com.odysii.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

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
                System.setProperty("webdriver.edge.driver",driverPath+"MicrosoftWebDriver.exe");
                driver =  new EdgeDriver();
                break;
            case OPERA:
                //add logic
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver",driverPath+"geckodriver.exe");
                driver = new FirefoxDriver();
                break;
                default:
                    return null;
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
