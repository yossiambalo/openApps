package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AppStore extends PageObject {
    @FindBy(className = "card")
    List<WebElement> apps;
    @FindBy(className = "cx-card-title")
    List<WebElement> appNames;
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
    public void addAppToLibrary(String appName){
        isElementPresent(appNames.get(0));
        for (WebElement element : appNames){
            if (appName.toLowerCase().trim().equals(element.getText().toLowerCase().trim())){
                element.click();
                break;
            }
        }
        if (!isElementPresent(addToLibrary)){
            removeFromLibrary.click();
        }
        isElementPresent(addToLibrary);
        addToLibrary.click();
        backNavigationButton.click();
    }
    public boolean isAppExist(String appName){
        boolean res = false;
        isElementPresent(appNames.get(0));
        for (WebElement element : appNames){
            if (appName.toLowerCase().trim().equals(element.getText().toLowerCase().trim())){
               res = true;
                break;
            }
        }
        return res;
    }

}
