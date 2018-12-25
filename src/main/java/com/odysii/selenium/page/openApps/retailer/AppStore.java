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
    @FindBy(id = "libraryApplicationDetailsRemoveFromLibraryButton")
    WebElement removeFromLibrary;
    @FindBy(id = "BackNavigationButton")
    WebElement backNavigationButton;
    public AppStore(WebDriver driver) {
        super(driver);
    }
    public int addAppToLibrary(int appIndex){
        int res = 0;
        scrollDown(apps.get(appIndex));
        isElementPresent(apps.get(appIndex));
        apps.get(appIndex).click();
         if (!isElementPresent(addToLibrary)){
             removeFromLibrary.click();
             res++;
         }
         isElementPresent(addToLibrary);
        addToLibrary.click();
        backNavigationButton.click();
        return res;
    }
    public void addAppToLibraryFromMainPage(int appIndex){
        apps.get(appIndex).click();

    }

}
