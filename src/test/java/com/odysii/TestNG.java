package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.MyApps;
import org.testng.annotations.Test;

public class TestNG extends TestBase{

    private final int WAIT = 2000;
    @Test
    public void test(){
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        wait(WAIT);
        homePage.getDashboardPage(driver);
        wait(WAIT);
        homePage.getPublicProfilePage(driver);
        wait(WAIT);
        homePage.getRevenueReportPage(driver);
        wait(WAIT);
        homePage.getSupportTicketstPage(driver);
        wait(WAIT);
        homePage.getTrasactionHistoryPage(driver);
        wait(WAIT);
    }
}
