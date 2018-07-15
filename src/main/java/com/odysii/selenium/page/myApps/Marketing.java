package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Properties;

public class Marketing extends PageObject {

    @FindBy(id = "app-promotion")
    private WebElement promotionalText;
    @FindBy(id = "app-keywords")
    private WebElement keywords;
    @FindBy(id = "screenshotsFile")
    private WebElement screenshotsFile;
    @FindBy(id = "iconFile")
    private WebElement appIcon;
    @FindBy(xpath = "//*[contains(text(), 'COMPLETE')]")
    private WebElement complete;

    public Marketing(WebDriver driver) {
        super(driver);
    }
    public void fillMarketing(){
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("marketing.properties");
        this.promotionalText.sendKeys(properties.getProperty("promotional_text"));
        this.keywords.sendKeys(properties.getProperty("keywords"));
        this.screenshotsFile.sendKeys(properties.getProperty("app_preview_screenshots"));
        this.appIcon.sendKeys(properties.getProperty("app_icon"));
        wait(2000);
        this.complete.click();
    }
    public void fillMarketing(String promotionalText,String kewords,String screenshotFilePath,String appIconPath){
        this.promotionalText.sendKeys(promotionalText);
        this.keywords.sendKeys(kewords);
        this.screenshotsFile.sendKeys(screenshotFilePath);
        this.appIcon.sendKeys(appIconPath);
        wait(2000);
        this.complete.click();
    }
}
