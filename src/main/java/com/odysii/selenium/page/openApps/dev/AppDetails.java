package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.PropertyLoader;
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
    @FindBy(xpath = "//div[contains(text(), 'Sports')]")
    WebElement sportsCategory;
    @FindBy(xpath = "//div[contains(text(), 'Shell')]")
    WebElement shellRetailer;
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

    private int WAIT = 200;
    public AppDetails(WebDriver driver) {
        super(driver);
    }
    public UploadCode setUpAppDetails(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("app_details.properties");
        this.name.sendKeys(properties.getProperty("name")+": "+dateFormat.format(date));
        this.appVersion.sendKeys(properties.getProperty("app_version"));
        this.subtitle.sendKeys(properties.getProperty("subtitle"));
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
    public void cancel(){
        if (cancel.isDisplayed())
        cancel.click();
    }
}
