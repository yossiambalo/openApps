package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.PropertyLoader;
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
    @FindBy(id = "finishButton")
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
        wait(4000);
        this.appIcon.sendKeys(properties.getProperty("app_icon"));
        wait(4000);
        if (!complete.isDisplayed()){
            pageUpDown(true);
        }
        this.complete.click();
    }
    public void fillMarketing(String promotionalText,String kewords,String screenshotFilePath,String appIconPath){
        this.promotionalText.sendKeys(promotionalText);
        this.keywords.sendKeys(kewords);
        this.screenshotsFile.sendKeys(getFile("application//"+screenshotFilePath));
        this.appIcon.sendKeys(getFile("application//"+appIconPath));
        wait(2000);
        if (!complete.isDisplayed()){
            pageUpDown(true);
        }
        this.complete.click();
    }
}
