package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.MyApps;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestNG {

    @Test
    public void test(){
        WebDriver driver = DriverManager.getWebdriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        System.out.println("jjhjh");
    }
}
