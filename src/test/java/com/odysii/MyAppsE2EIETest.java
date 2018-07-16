package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import org.testng.annotations.BeforeClass;

public class MyAppsE2EIETest extends MyAppsE2EChromeTest {
    @BeforeClass
    public void init(){
        driver = DriverManager.getWebDriver(DriverType.IE);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
    }
}
