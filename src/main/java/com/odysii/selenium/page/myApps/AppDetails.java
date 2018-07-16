package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

public class AppDetails extends PageObject{

    @FindBy(id = "app-name")
    WebElement name;
    @FindBy(id = "app-subtitle")
    WebElement subtitle;
    @FindBy(id = "app-language")
    WebElement language;
    @FindBy(id = "app-category")
    WebElement category;
    @FindBy(id = "app-retailers")
    WebElement retailer;
    @FindBy(id = "app-availability")
    WebElement availability;
    @FindBy(xpath = "//*[contains(text(), 'CANCEL')]")
    WebElement cancel;
    @FindBy(xpath = "//*[contains(text(), 'CONTINUE')]")
    WebElement next;
    public AppDetails(WebDriver driver) {
        super(driver);
    }
    public UploadCode setUpAppDetails(){
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("app_details.properties");
        this.name.sendKeys(properties.getProperty("name"));
        this.subtitle.sendKeys(properties.getProperty("subtitle"));
        this.language.sendKeys(properties.getProperty("language"));
        this.category.sendKeys(properties.getProperty("category"));
        this.availability.sendKeys(properties.getProperty("availability"));
        this.retailer.sendKeys(properties.getProperty("retailer"));
        this.next.click();
        return new UploadCode(webDriver);
    }
    public UploadCode setUpAppDetails(String name,String subtitle,String language,String category,String availability, String retailer){
        this.name.sendKeys(name);
        this.subtitle.sendKeys(subtitle);
        this.language.sendKeys(language);
        this.category.sendKeys(category);
        this.availability.sendKeys(availability);
        this.retailer.sendKeys(retailer);
        this.next.click();
        return new UploadCode(webDriver);
    }
}
