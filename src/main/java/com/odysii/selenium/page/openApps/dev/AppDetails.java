package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.PropertyLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    @FindBy(xpath = "//div[contains(text(), 'Sports')]")
    WebElement sportsCategory;
    @FindBy(xpath = "//div[contains(text(), 'Weather')]")
    WebElement weatherCategory;
    @FindBy(xpath = "//div[contains(text(), 'Shell')]")
    WebElement shellRetailer;
    @FindBy(xpath = "//div[contains(text(), 'ExxonMobil')]")
    WebElement exxonMobilRetailer;
    @FindBy(className = "dropdown-btn")
    List<WebElement> dropDown;
    @FindBy(id = "AppCategories")
    WebElement category;
    @FindBy(id = "appRetailers")
    WebElement retailer;
    @FindBy(id = "appAvailability")
    WebElement availability;
    @FindBy(id = "cancel-button")
    WebElement cancel;
    @FindBy(id = "nextButton")
    WebElement next;
    @FindBy(id = "appPriceType")
    WebElement appPriceType;
    @FindBy(id = "appPrice")
    WebElement appPrice;

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
    public UploadCode setUpAppDetails(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("app_details.properties");
        this.appTitle = properties.getProperty("name");
        this.appDescription = properties.getProperty("subtitle");
        isElementPresent(name);
        this.name.sendKeys(appTitle +": "+dateFormat.format(date));
        this.appVersion.sendKeys(properties.getProperty("app_version"));
        this.subtitle.sendKeys(appDescription);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.englishLanguage);
        wait(WAIT);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",sportsCategory);
        wait(WAIT);
        this.availability.sendKeys(properties.getProperty("availability"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].blur();",availability);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",shellRetailer);
        appPriceType.sendKeys(properties.getProperty("app_price_type"));
        appPrice.sendKeys(properties.getProperty("app_price"));
        wait(WAIT);
        pageUpDown(true);
        wait(2000);
        this.next.click();
        return new UploadCode(webDriver);
    }
    public UploadCode setUpAppDetails(String name,String subtitle,String language,String category,String availability, String retailer){
        wait(3000);
        this.name.sendKeys(name);
        this.subtitle.sendKeys(subtitle);
        this.language.sendKeys(language);
        this.category.sendKeys(category);
        this.availability.sendKeys(availability);
        this.retailer.sendKeys(retailer);
        this.next.click();
        return new UploadCode(webDriver);
    }
    /**
     * Method porpose: for adding a new version
     * @param version
     * @return
     */
    public UploadCode setUpAppDetails(String version){
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
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",sportsCategory);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",weatherCategory);
        pageUpDown(true);
        wait(WAIT);
        this.availability.sendKeys(properties.getProperty("availability"));
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].blur();",availability);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",shellRetailer);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",exxonMobilRetailer);
        //appPriceType.sendKeys(properties.getProperty("app_price_type"));
        //ToDo: find elements should be removed once id will unique
        webDriver.findElements(By.id("appPriceType")).get(1).sendKeys(properties.getProperty("app_price_type"));
        //ToDo: find elements should be removed once id will unique
        webDriver.findElements(By.id("appPrice")).get(1).sendKeys(properties.getProperty("app_price"));
        //appPrice.sendKeys(properties.getProperty("app_price"));
        wait(WAIT);
        pageUpDown(true);
        wait(2000);
        //ToDo: find elements should be removed once id will unique
        //webDriver.findElements(By.id("nextButton")).get(1).click();
        this.next.click();
        return new UploadCode(webDriver);
    }
    public void cancel(){
        if (cancel.isDisplayed())
            cancel.click();
    }
}
