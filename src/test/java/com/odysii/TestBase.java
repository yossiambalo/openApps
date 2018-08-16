package com.odysii;


import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.util.DriverManager;
import com.odysii.selenium.page.util.DriverType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {
    User user;
    WebDriver driver;
    DevHomePage homePage;
    protected final int WAIT = 2000;
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

    @BeforeClass
    public void login() {
        user = new User(driver);
        user.login("user", "123456");
    }
    @Parameters("browser")
    @BeforeTest
    public void init(String browser){
        switch (browser){
            case "chrome":
                driver = DriverManager.getWebDriver(DriverType.CHROME);
                break;
            case "ie":
                driver = DriverManager.getWebDriver(DriverType.IE);
                break;
            case "firefox":
                driver = DriverManager.getWebDriver(DriverType.FIREFOX);
                break;
            default:
        }
        driver.get("http://openappsqa.tveez.local:8080/openAppStore");
        homePage = new DevHomePage(driver);
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
}
