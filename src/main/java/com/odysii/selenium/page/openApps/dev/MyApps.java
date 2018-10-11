package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyApps extends PageObject {

    private static final String APP_TITLE = "cx-card-title";
    private static final String APP_DESCRIPTION = "card-text";
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
    public ShowUp showUp(WebElement appElement){
        appElement.click();
        return new ShowUp(webDriver);
    }
    public String getTitle(int appIndex){
        return apps.get(appIndex).findElement(By.className(APP_TITLE)).getText();
    }
    public String getDescription(int appIndex){
        return apps.get(appIndex).findElement(By.className(APP_DESCRIPTION)).getText();
    }
}
