package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AppLibrary extends PageObject {
    @FindBy(className = "card")
    List<WebElement> apps;
    @FindBy(id = "libraryApplicationDetailsRemoveFromLibraryButton")//BackNavigationButton
    WebElement removeFromLibrary;
    @FindBy(id = "BackNavigationButton")
    WebElement backNavigationButton;

    public AppLibrary(WebDriver driver) {
        super(driver);
    }
    public void removeAppFromLibrary(int appIndex){
        scrollDown(apps.get(appIndex));
        isElementPresent(apps.get(appIndex));
        apps.get(appIndex).click();
        wait(WAIT);
        removeFromLibrary.click();
        //backNavigationButton.click();
    }
}
