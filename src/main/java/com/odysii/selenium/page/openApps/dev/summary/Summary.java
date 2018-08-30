package com.odysii.selenium.page.openApps.dev.summary;

import com.odysii.selenium.page.util.FieldType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Summary extends PageObject {
    @FindBy(id = "app-name")
    WebElement name;
    @FindBy(id = "appSubtitleSummary")
    WebElement subtitle;
    @FindBy(id = "languageSummary")
    WebElement language;
    @FindBy(xpath = "//div[contains(text(), 'English')]")
    WebElement englishLanguage;
    @FindBy(xpath = "//div[contains(text(), 'French')]")
    WebElement frenchLanguage;
    @FindBy(xpath = "//div[contains(text(), 'Sports')]")
    WebElement sportsCategory;
    @FindBy(xpath = "//div[contains(text(), 'Weather')]")
    WebElement newsAndWeather;
    @FindBy(xpath = "//div[contains(text(), 'ExxonMobil')]")
    WebElement exxonMobil;
    @FindBy(id = "categorySummary")
    WebElement category;
    @FindBy(id = "retailersSummary")
    WebElement retailer;
    @FindBy(id = "appAvailabilitySummary")
    WebElement availability;
    @FindBy(id = "codeFileSummary")
    WebElement uploadCode;
    @FindBy(id = "promotionalTextSummary")
    WebElement promotionalTxt;
    @FindBy(id = "appKeywordsSummary")
    WebElement appKeywordsSummary;
    @FindBy(id = "appPrice")
    WebElement appPrice;
    @FindBy(id = "appPriceType")
    WebElement appPriceType;
    @FindBy(id = "iconFileSummary")
    WebElement iconFileSummary;
    @FindBy(id = "screenshotsFileSummary")
    WebElement screenshotsFileSummary;
    @FindBy(id = "appSummeryCertify")
    WebElement certify;
    @FindBy (id = "finishButton")
    WebElement finishBtn;
    @FindBy(id = "finishButton")
    WebElement finishButtonCertify;


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

    /**
     * Edit application, field value are hardcoded, enough for testing this functionality
     */
    public void editSummary() {
        this.uploadCode.sendKeys(getFile("application//TH.zip"));
        ((JavascriptExecutor)webDriver).executeScript("document.getElementById('appSubtitleSummary').value = '';");
        ((JavascriptExecutor)webDriver).executeScript("document.getElementById('appSubtitleSummary').value = 'Edit subtitle';");
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.englishLanguage);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.frenchLanguage);
        //Category field
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.sportsCategory);
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.newsAndWeather);
        promotionalTxt.clear();
        promotionalTxt.sendKeys("Promotional text!");
        appKeywordsSummary.clear();
        appKeywordsSummary.sendKeys("One ke, Two key");

        this.availability.sendKeys("Public");
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].blur();",availability);
        //Retailer field
        ((JavascriptExecutor)webDriver).executeScript("arguments[0].click();",this.exxonMobil);
        pageUpDown(true);
        wait(WAIT);
        appPrice.sendKeys("2");
        appPriceType.sendKeys("Per site per year");
        this.iconFileSummary.sendKeys(getFile("application//dog2.jpg"));
        this.screenshotsFileSummary.sendKeys(getFile("application//dog2.jpg"));
        finishBtn.click();
    }
    public void certifyApp(){
        certify.click();
        wait(WAIT);
        finishBtn.click();
        wait(WAIT);
        finishButtonCertify.click();


    }

}
