package com.odysii.selenium.page;


import org.openqa.selenium.WebDriver;

public class AppStore {

    static final String URI = "http://openapps.tveez.local:8080/openAppStore/front";
    public static MyApps getMyAppsInstance(WebDriver driver){
        driver.get(URI+"/my-apps");
        return new MyApps(driver);
    }
    public static MyApps getDashboardInstance(WebDriver driver){
        driver.get(URI+"/dashboard");
        return new MyApps(driver);
    }
}
