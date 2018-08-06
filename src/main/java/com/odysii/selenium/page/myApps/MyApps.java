package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.myApps.summary.ShowUp;
import com.odysii.selenium.page.myApps.summary.Summary;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyApps extends PageObject {

    @FindBy(id = "newAppButton")
    WebElement addNewApp;
    @FindBy(className = "card")
    List<WebElement> apps;
    public MyApps(WebDriver driver) {
        super(driver);
    }
    public AppDetails clickAddNewAppBtn(){
        this.addNewApp.click();
        return new AppDetails(webDriver);
    }
    public ShowUp showUp(int appIndex){
        apps.get(appIndex).click();
        return new ShowUp(webDriver);
    }
}
