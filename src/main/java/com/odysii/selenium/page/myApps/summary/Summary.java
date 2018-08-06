package com.odysii.selenium.page.myApps.summary;

import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Summary extends PageObject {
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
    @FindBy(id = "cancel-button")
    WebElement cancel;
    @FindBy(id = "next-button")
    WebElement next;
    @FindBy(id = "appSummeryCertify")
    WebElement certify;

    public Summary(WebDriver driver) {
        super(driver);
    }
    public void editSummary(FieldType type, String newValue) {
        switch (type) {
            case APPNAME:
                this.name.clear();
                this.name.sendKeys(newValue);
                break;
            case CATEGORY:
                this.category.clear();
                this.category.sendKeys(newValue);
                break;
            case LANGUAGE:
                this.language.clear();
                this.language.sendKeys(newValue);
                break;
            case SUBTITLE:
                this.subtitle.clear();
                this.subtitle.sendKeys(newValue);
                break;
            case RETAILERS:
                this.retailer.clear();
                this.retailer.sendKeys(newValue);
                break;
            case AVAILABILITY:
                this.availability.clear();
                this.availability.sendKeys(newValue);
                break;
            default:
                ////
        }
    }

    public void certifyApp(){
    certify.click();

    }
}
