package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppVersionsTest extends TestBase{
    private final int WAIT = 2000;
    private final String cancelTxt = "CANCEL";
    private final String backTxt = "BACK";
    private final String continueTxt = "CONTINUE";
    private final String finishTxt = "FINISH";

    @BeforeClass
    public void init() {
        driver = DriverManager.getWebDriver(DriverType.CHROME);
        driver.get("http://openapps.tveez.local:8080/openAppStore");
    }
    @Test
    public void _001_

}
