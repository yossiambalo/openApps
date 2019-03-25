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
    private static final String APP_DESCRIPTION = "card-text";//cx-status
    private static final String APP_STATUS = "cx-status";
    @FindBy(id = "newAppButton")
    WebElement addNewApp;
    @FindBy(className = "card")
    List<WebElement> apps;

    public MyApps(WebDriver driver) {
        super(driver);
    }
    public AppDetails clickAddNewAppBtn(){
        isElementPresent(addNewApp);
        this.addNewApp.click();
        return new AppDetails(webDriver);
    }
    public ShowUp showUp(int appIndex){
        scrollDown(apps.get(appIndex));
        isElementPresent(apps.get(appIndex));
        apps.get(appIndex).click();
        return new ShowUp(webDriver);
    }
    public ShowUp showUp(WebElement appElement){
        scrollDown(appElement);
        isElementPresent(appElement);
        wait(WAIT);
        appElement.click();
        return new ShowUp(webDriver);
    }
    public String getTitle(int appIndex){
        return apps.get(appIndex).findElement(By.className(APP_TITLE)).getText();
    }
    public String getDescription(int appIndex){
        return apps.get(appIndex).findElement(By.className(APP_DESCRIPTION)).getText();
    }
    public String getApplicationStatus(int appIndex){
        return apps.get(appIndex).findElement(By.className(APP_STATUS)).getText();
    }
}
