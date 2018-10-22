package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AppStore extends PageObject {
    @FindBy(className = "card")
    List<WebElement> apps;
    @FindBy(id = "StoreApplicationDetailsAddToLibraryButton")
    WebElement addToLibrary;
    public AppStore(WebDriver driver) {
        super(driver);
    }
    public void addAppToLibrary(int appIndex){
        apps.get(appIndex).click();
        isElementPresent(addToLibrary);
        addToLibrary.click();
    }
}
