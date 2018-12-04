package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.PropertyLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
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
    @FindBy(id = "finishButton")
    private List<WebElement> completes;
    @FindBy(id = "addAppDeleteScreenshot0")
    private WebElement deleteBtn;

    public Marketing(WebDriver driver) {
        super(driver);
    }
    public void fillMarketing(){
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadPropFile("marketing.properties");
        this.promotionalText.sendKeys(properties.getProperty("promotional_text"));
        isElementPresent(keywords);
        this.keywords.sendKeys(properties.getProperty("keywords"));
        isElementPresent(appIcon);
        ((JavascriptExecutor)  webDriver).executeScript("document.getElementById('iconFile').removeAttribute('class')");
        this.appIcon.sendKeys(getFile(properties.getProperty("app_icon")));
        isElementPresent(screenshotsFile);
        ((JavascriptExecutor)  webDriver).executeScript("document.getElementById('screenshotsFile').removeAttribute('class')");
        this.screenshotsFile.sendKeys(getFile(properties.getProperty("app_preview_screenshots")));
        wait(WAIT);
        scrollDown(deleteBtn);
        scrollDown(complete);
        isElementPresent(complete);
        this.complete.click();
    }
    public void fillMarketing(String promotionalText,String keywords,String screenshotFileName,String appIconFileName){
        isElementPresent(this.promotionalText);
        this.promotionalText.clear();
        this.promotionalText.sendKeys(promotionalText);
        isElementPresent(this.keywords);
        this.keywords.sendKeys(keywords);
        isElementPresent(this.screenshotsFile);
        this.screenshotsFile.sendKeys(getFile("image//"+screenshotFileName));
        isElementPresent(this.appIcon);
        this.appIcon.sendKeys(getFile("image//"+appIconFileName));
        scrollDown(complete);
        this.complete.click();
    }
}
