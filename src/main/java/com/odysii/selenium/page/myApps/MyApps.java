package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyApps extends PageObject {

    @FindBy(xpath = "//*[contains(text(), 'ADD NEW APP')]")
    WebElement addNewApp;
    MyApps(WebDriver driver) {
        super(driver);
    }
    public AppDetails clickAddNewAppBtn(){
        this.addNewApp.click();
        return new AppDetails(webDriver);
    }

}
