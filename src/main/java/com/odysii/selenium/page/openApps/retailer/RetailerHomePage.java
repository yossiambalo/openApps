package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.ExplicitAssertionError;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RetailerHomePage extends PageObject {
    @FindBy(id = "navItem10")
    WebElement appStore;
    @FindBy(id = "navItem11")
    WebElement appLibrary;
    @FindBy(id = "navItem12")
    WebElement campaigns;
    @FindBy(id = "navItem13")
    WebElement scheduling;
    @FindBy(id = "navItem14")
    WebElement manageKeys;
    @FindBy(id = "applicationSearchInput")
    WebElement applicationSearchInput;
    @FindBy(id = "makeApplicationSearch")
    WebElement makeApplicationSearch;
    @FindBy(id = "BackNavigationButton")
    WebElement backNavigationButton;

    private final static String STOREAPP_PREFIX = "storeApp";

    public RetailerHomePage(WebDriver driver) {
        super(driver);
    }

    public AppStore getAppStore() {
        isElementPresent(appStore);
        appStore.click();
        return new AppStore(webDriver);
    }

    public AppLibrary getAppLibrary() {
        isElementPresent(appLibrary);
        appLibrary.click();
        return new AppLibrary(webDriver);
    }

    public Campaign getCampaign() {
        if (isElementPresent(backNavigationButton)) {
            backNavigationButton.click();
        }
        isElementPresent(campaigns);
        campaigns.click();
        return new Campaign(webDriver);
    }

    public Scheduling getScheduling() {
        isElementPresent(scheduling);
        scheduling.click();
        return new Scheduling(webDriver);
    }

    public KeyManagement getKeysMGMT() {
        isElementExist(By.id("manageKeys"));
        manageKeys.click();
        return new KeyManagement(webDriver);
    }
    public void searchApps(String keyWord){
        isElementPresent(applicationSearchInput);
        applicationSearchInput.sendKeys(keyWord);
        isElementPresent(makeApplicationSearch);
        makeApplicationSearch.click();
    }
    public RetailerHomePage getAppDetails(int index) {
        webDriver.findElement(By.id(STOREAPP_PREFIX+index)).click();
        return new RetailerHomePage(webDriver);
    }

}

