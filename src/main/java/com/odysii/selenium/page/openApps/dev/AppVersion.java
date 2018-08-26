package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.FieldType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppVersion extends PageObject {
    @FindBy(id = "edit-app-new-version")
    WebElement newVersion;
    @FindBy(id = "codeFile")
    WebElement selectUpload;
    @FindBy(id = "newVersionNumber")
    WebElement newVersionNumber;
    @FindBy(id = "new-version-text")
    WebElement newVersionText;
    @FindBy(id = "finish-button")
    public WebElement finishButton;


    public AppVersion(WebDriver driver){
        super(driver);

    }
    public void editAppVersion(FieldType type, String newValue) {
        switch (type) {

            case NEW_VERSION_NUMBER:
                newVersion.click();
                this.newVersionNumber.clear();
                this.newVersionNumber.sendKeys(newValue);
                break;
            case NEW_VERSION_TEXT:
                newVersion.click();
                this.newVersionText.clear();
                this.newVersionText.sendKeys(newValue);
                break;
            case SELECT_UPLOAD:
                newVersion.click();
                this.selectUpload.sendKeys(newValue);
                break;

            default:

        }
    }
        public void editAppVersion(String appVersion, String newVersionText, String selectUpload){
            newVersion.click();
            this.newVersionNumber.clear();
            this.newVersionNumber.sendKeys(appVersion);
            this.newVersionText.clear();
            this.newVersionText.sendKeys(newVersionText);
            this.selectUpload.sendKeys(selectUpload);

        }
    }


