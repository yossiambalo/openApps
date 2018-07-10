package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.AppDetails;
import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.MyApps;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
;

public class MyAppsTest extends TestBase{

    private final int WAIT = 2000;
    @BeforeClass
    public void init(){
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
//        wait(WAIT);
//        homePage.getDashboardPage(driver);
//        wait(WAIT);
//        homePage.getPublicProfilePage(driver);
//        wait(WAIT);
//        homePage.getRevenueReportPage(driver);
//        wait(WAIT);
//        homePage.getSupportTicketstPage(driver);
//        wait(WAIT);
//        homePage.getTrasactionHistoryPage(driver);
//        wait(WAIT);
    }
    @Test
    public void _001_add_new_app(){
        HomePage homePage = new HomePage(driver);
        MyApps myApps = homePage.getMyAppsPage(driver);
        wait(WAIT);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        appDetails.setUpAppdetails();
    }
}
