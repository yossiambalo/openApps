package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.FieldType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppVersion extends PageObject {
    @FindBy(id = "edit-app-new-version")
    WebElement NewVersion;
    @FindBy(id = "appVersionsUploadCode")
    WebElement SelectUpload;
    @FindBy(id = "new-version-number")
    WebElement NewVeriosnNumber;
    @FindBy(id = "new-version-text")
    WebElement NewVersionText;
    @FindBy(id = "finish-button")
    WebElement FinishButton;


    public AppVersion(WebDriver driver){
        super(driver);

    }
    public void editAppVersion(FieldType type, String newValue){
    switch (type){

        case NEW_VERSION_NUMBER:
            NewVersion.click();
            this.NewVeriosnNumber.clear();
            this.NewVeriosnNumber.sendKeys(newValue);
            break;
        case NEW_VERSION_TEXT:
            this.NewVersionText.clear();
            this.NewVersionText.sendKeys(newValue);
            break;
        case SELECT_UPLOAD:
            this.SelectUpload.sendKeys(newValue);
            break;

            default:


        }
    }
}
