package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.FieldType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppVersion extends PageObject {
    @FindBy(id = "editAppNewVersion")
    WebElement newVersion;
    @FindBy(id = "codeFile")
    WebElement selectUpload;
    @FindBy(id = "appVersion")
    WebElement newVersionNumber;
    @FindBy(id = "appVersion")
    WebElement newVersionText;
    @FindBy (id = "appName")
    WebElement appName;
    @FindBy (id = "appSubtitle")
    WebElement appSubtitle;
    @FindBy (id = "appAvailability")
    WebElement availability;
    @FindBy(id = "app-promotion")
    WebElement promotionText;
    @FindBy(id = "app-keywords")
    WebElement keywords;
    @FindBy(id = "newAppUploadIcon")
    WebElement appIcon;
    @FindBy (id = "newAppUploadScreenshot")
    WebElement screenShots;
    @FindBy (id = "finishButton")
    WebElement finishCreationNewVersion;
    @FindBy (id = "nextButton")
    WebElement continueButton;
    @FindBy (id = "previousButton ")
    WebElement backButton;

    public WebElement finishButton;


    public AppVersion(WebDriver driver){
        super(driver);

    }
    public void editAppVersion(FieldType type, String newValue) {
        switch (type) {

            case NEW_VERSION_NUMBER:
                this.newVersionNumber.clear();
                this.newVersionNumber.sendKeys(newValue);
                break;
            case NEW_VERSION_TEXT:
                this.newVersionText.clear();
                this.newVersionText.sendKeys(newValue);
                break;
            case SELECT_UPLOAD:
                this.selectUpload.sendKeys(getFile("application//"+newValue ));
                break;

            default:

        }
    }
//        public void editAppVersion(String appVersion, String newVersionText, String selectUpload){
//            newVersion.click();
//            this.newVersionNumber.clear();
//            this.newVersionNumber.sendKeys(appVersion);
//            this.newVersionText.clear();
//            this.newVersionText.sendKeys(newVersionText);
//            this.selectUpload.sendKeys(selectUpload);

    public void setAppName(WebElement appName) {
        this.appName = appName;
    }

    public void clickNewVersion(){
        newVersion.click();

    }

}


