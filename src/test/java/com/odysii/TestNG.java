package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import com.odysii.selenium.page.AppStore;
import com.odysii.selenium.page.MyApps;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class TestNG {

    @Test
    public void test(){
        WebDriver driver = DriverManager.getWebdriver(DriverType.CHROME);
        MyApps myApps = AppStore.clickMyAppsLink(driver);
        System.out.println("jjhjh");
    }
}
