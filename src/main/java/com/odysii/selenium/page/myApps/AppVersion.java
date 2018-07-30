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
    @FindBy(id = "newVersionNumber")
    WebElement NewVersionNumber;
    @FindBy(id = "new-version-text")
    WebElement NewVersionText;
    @FindBy(id = "finish-button")
    WebElement FinishButton;


    public AppVersion(WebDriver driver){
        super(driver);

    }
    public void editAppVersion(FieldType newVersionText, String s, FieldType type, String newValue, FieldType selectUpload) {
        switch (type) {

            case NEW_VERSION_NUMBER:
                NewVersion.click();
                this.NewVersionNumber.clear();
                this.NewVersionNumber.sendKeys(newValue);
                break;
            case NEW_VERSION_TEXT:
                NewVersion.click();
                this.NewVersionText.clear();
                this.NewVersionText.sendKeys(newValue);
                break;
            case SELECT_UPLOAD:
                NewVersion.click();
                this.SelectUpload.sendKeys(newValue);
                break;

            default:

        }
    }
        public void editAppVersion(String appVersion, String newVersionText, String selectUpload){
            NewVersion.click();
            this.NewVersionNumber.clear();
            this.NewVersionNumber.sendKeys(appVersion);
            this.NewVersionText.clear();
            this.NewVersionText.sendKeys(newVersionText);
            this.SelectUpload.sendKeys(selectUpload);

        }
    }


