package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.openApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class AppDetails extends PageObject{

    @FindBy(id = "appName")
    public WebElement name;
    @FindBy(id = "appVersion")
    public WebElement appVersion;
    @FindBy(id = "appSubtitle")
    WebElement subtitle;
    @FindBy(id = "appLanguages")
    WebElement language;
    @FindBy(xpath = "//div[contains(text(), 'English')]")
    WebElement englishLanguage;
    @FindBy(xpath = "//div[contains(text(), 'French')]")
    WebElement frenchLanguage;
    @FindBy(xpath = "//div[contains(text(), 'Merchandising')]")
    WebElement merchandising;
    @FindBy(xpath = "//div[contains(text(), 'Interactive')]")
    WebElement interactive;
    @FindBy(xpath = "//div[contains(text(), 'Media')]")
    WebElement media;
    @FindBy(xpath = "//div[contains(text(), 'Foodservice')]")
    WebElement foodservice;
    @FindBy(xpath = "//div[contains(text(), 'Loyalty')]")
    WebElement loyalty;
    @FindBy(xpath = "//div[contains(text(), 'Services')]")
    WebElement services;
    @FindBy(xpath = "//div[contains(text(), 'Charity')]")
    WebElement charity;
    @FindBy(xpath = "//div[contains(text(), 'Other')]")
    WebElement other;
    @FindBy(xpath = "//div[contains(text(), 'Shell')]")
    WebElement shellRetailer;
    @FindBy(xpath = "//div[contains(text(), 'ExxonMobil')]")
    WebElement exxonMobilRetailer;
    @FindBy(id = "appRetailers")
    WebElement retailerFirstOption;
    @FindBy(className = "dropdown-btn")
    List<WebElement> dropDown;
    @FindBy(id = "AppCategories")
    WebElement category;
    @FindBy(id = "appRetailers")
    WebElement retailer;
    @FindBy(id = "appAvailability")
    WebElement availability;
    @FindBy(id = "cancelButton")
    WebElement cancel;
    @FindBy(id = "nextButton")
    WebElement next;
    @FindBy(id = "appPriceType")
    WebElement appPriceType;
    @FindBy(id = "appPrice")//invalid-feedback-multiple
    private WebElement appPrice;
    @FindBy(css = "#autoAssign .switch")//invalid-feedback-multiple
    private WebElement autoAddRadioBtn;
    @FindBy(css = "#storeHidden .slider")
    private WebElement hideAppInStore;
    @FindBy(id = "invalid-feedback-multiple")
    WebElement isAvailabilityChecked;


    public String getAppTitle() {
        return appTitle;
    }

    public String getAppDescription() {
        return appDescription;
    }

    private String appTitle,appDescription;
    private int WAIT = 200;
    public AppDetails(WebDriver driver) {
        super(driver);
    }
    public Dependencies setUpAppDetailsFromPropFile(String propFile){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile(propFile);
        this.appTitle = properties.getProperty("name");
        this.appDescription = properties.getProperty("subtitle");
        isElementPresent(name);
        this.name.sendKeys(appTitle +": "+dateFormat.format(date));
        this.appVersion.sendKeys(properties.getProperty("app_version"));
        this.subtitle.sendKeys(appDescription);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.englishLanguage);
        wait(WAIT);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",charity);
        wait(WAIT);
//        this.availability.sendKeys(properties.getProperty("availability"));
//        ((JavascriptExecutor)webDriver).executeScript("arguments[0].blur();",availability);
        Select realSelect = new Select(availability);
        realSelect.selectByIndex(1);
        //((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",retailerFirstOption.findElements(By.className("multiselect-item-checkbox")).get(0).findElements(By.tagName("div")).get(0));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//div[contains(text(), '"+properties.getProperty("retailer")+"')]")));
        appPriceType.sendKeys(properties.getProperty("app_price_type"));
        appPrice.sendKeys(properties.getProperty("app_price"));
        scrollDown(next);
        isElementPresent(next);
        this.next.click();
        return new Dependencies(webDriver);
    }

    public Dependencies setUpAppDetails(String name, String version, String subtitle, String priceType, String price, AvailabilityType availabilityType,boolean autoAdd,boolean hideInAppStrore){
        try {
            isElementPresent(this.name);
            this.name.clear();
            this.name.sendKeys(name);
            this.appVersion.sendKeys(version);
            this.subtitle.sendKeys(subtitle);
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.englishLanguage);
            wait(WAIT);
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",charity);
            wait(WAIT);
//        if (availability != null || !availability.isEmpty()){
//            this.availability.sendKeys(availability);
//        }
           if (availabilityType != null){
               this.availability.sendKeys(availabilityType.getAvailability());
               if (AvailabilityType.PRIVATE.equals(availabilityType.getAvailability())) {
                   ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", this.shellRetailer);
               }
           }
            wait(3000);
            appPriceType.sendKeys(priceType);
            appPrice.sendKeys(price);
            scrollDown(next);
            if (autoAdd){
                autoAddRadioBtn.click();
            }
            if (hideInAppStrore){
                this.hideAppInStore.click();
            }
            wait(3000);
            this.next.click();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
            return null;
        }
        return new Dependencies(webDriver);
    }

    /**
     * Method porpose: for adding a new version
     * @param version
     * @return
     */
    public Dependencies setUpAppDetails(String version){
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("app_details.properties");
        isElementPresent(appVersion);
        appVersion.clear();
        this.appVersion.sendKeys(version);
        subtitle.clear();
        this.subtitle.sendKeys(properties.getProperty("subtitle"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.englishLanguage);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.frenchLanguage);
        wait(WAIT);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",charity);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",other);
        scrollDown(availability);
        this.availability.sendKeys(properties.getProperty("availability"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].blur();",availability);
//        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",shellRetailer);
//        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",exxonMobilRetailer);
        if (isElementPresent(By.className("invalid-feedback-multiple"))) {
            //((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", retailerFirstOption.findElements(By.className("multiselect-item-checkbox")).get(0).findElements(By.tagName("div")).get(0));
            //((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", retailerFirstOption.findElements(By.className("multiselect-item-checkbox")).get(0).findElements(By.tagName("div")).get(0));
            ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//div[contains(text(), '"+properties.getProperty("retailer")+"')]")));
        }
        //appPriceType.sendKeys(properties.getProperty("app_price_type"));
        //ToDo: find elements should be removed once id will unique
        webDriver.findElement(By.id("appPriceType")).sendKeys(properties.getProperty("app_price_type"));
        //ToDo: find elements should be removed once id will unique
        webDriver.findElement(By.id("appPrice")).sendKeys(properties.getProperty("app_price"));
        scrollDown(next);
        this.next.click();
        return new Dependencies(webDriver);
    }

    public void cancel(){
        scrollDown(cancel);
        if (cancel.isDisplayed())
            cancel.click();
    }
    boolean isEmptyString(String string) {
        return string == null || string.isEmpty();
    }
}


