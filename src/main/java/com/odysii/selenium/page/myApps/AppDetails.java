package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Properties;

public class AppDetails extends PageObject{

    @FindBy(id = "app-name")
    public WebElement name;
    @FindBy(id = "appVersion")
    public WebElement appVersion;
    @FindBy(id = "app-subtitle")
    WebElement subtitle;
    @FindBy(className = "dropdown-btn")
    WebElement language;
    @FindBy(xpath = "//div[contains(text(), 'English')]")
    WebElement englishLanguage;
    @FindBy(xpath = "//div[contains(text(), 'Sports')]")
    WebElement sportsCategory;
    @FindBy(xpath = "//div[contains(text(), 'Shell')]")
    WebElement shellRetailer;
    @FindBy(className = "dropdown-btn")
    List<WebElement> dropDown;
    @FindBy(xpath = "//span[contains(text(), 'Select categories')]")
    WebElement category;
    @FindBy(xpath = "//span[contains(text(), 'Select retailers')]")
    WebElement retailer;
    @FindBy(id = "app-availability")
    WebElement availability;
    @FindBy(id = "cancel-button")
    WebElement cancel;
    @FindBy(id = "next-button")
    WebElement next;
    public AppDetails(WebDriver driver) {
        super(driver);
    }
    public UploadCode setUpAppDetails(){
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("app_details.properties");
        this.name.sendKeys(properties.getProperty("name"));
        this.appVersion.sendKeys(properties.getProperty("app_version"));
        this.subtitle.sendKeys(properties.getProperty("subtitle"));
        this.dropDown.get(0).click();
        this.englishLanguage.click();
        this.dropDown.get(0).click();
        this.dropDown.get(1).click();
        this.sportsCategory.click();
        this.dropDown.get(1).click();
        this.dropDown.get(2).click();
        this.shellRetailer.click();
        this.dropDown.get(2).click();
//        this.category.sendKeys(properties.getProperty("category"));
        this.availability.sendKeys(properties.getProperty("availability"));
//        this.retailer.sendKeys(properties.getProperty("retailer"));
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
