package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.ExplicitAssertionError;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RetailerHomePage extends PageObject {
    @FindBy(xpath = "//span[contains(text(), 'App Store')]")
    WebElement appStore;
    @FindBy(xpath = "//span[contains(text(), 'App Library')]")
    WebElement appLibrary;
    @FindBy(xpath = "//span[contains(text(), 'Campaigns')]")
    WebElement campaigns;
    @FindBy(xpath = "//span[contains(text(), 'Scheduling')]")
    WebElement scheduling;
    @FindBy(id = "applicationSearchInput")
    WebElement applicationSearchInput;
    @FindBy(id = "makeApplicationSearch")
    WebElement makeApplicationSearch;
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

    public Campaign getCampaigs() {
        isElementPresent(campaigns);
        campaigns.click();
        return new Campaign(webDriver);
    }

    public Scheduling getScheduling() {
        scheduling.click();
        return new Scheduling(webDriver);
    }
    public void searchApps(String keyWord){
        applicationSearchInput.sendKeys(keyWord);
        isElementPresent(makeApplicationSearch);
        makeApplicationSearch.click();
    }
    public RetailerHomePage getAppDetails(int index) {
        webDriver.findElement(By.id(STOREAPP_PREFIX+index)).click();
        return new RetailerHomePage(webDriver);
    }

}

