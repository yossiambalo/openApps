package com.odysii.selenium.page.myApps.summary;

import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static com.odysii.selenium.page.FieldType.KEYWORDS;
import static com.odysii.selenium.page.FieldType.PROMOTIONAL_TOKEN;

public class Marketing extends PageObject {

    @FindBy(id = "appName")
    WebElement promotionalToken;
    @FindBy(id = "appSubtitle")
    WebElement keywords;
    @FindBy(id ="screenshotsFile")
    WebElement screenshotsFile;

    public Marketing(WebDriver driver) {
        super(driver);
    }
    public void editMarketing(FieldType type, String newValue){
        switch (type){
            case PROMOTIONAL_TOKEN:
                this.promotionalToken.clear();
                this.promotionalToken.sendKeys(newValue);
                break;
            case KEYWORDS:
                this.keywords.clear();
                this.keywords.sendKeys(newValue);
                break;
            case SCREEN_SHOTS_FILE:
                this.screenshotsFile.sendKeys(newValue);
                break;

        }
    }
}
