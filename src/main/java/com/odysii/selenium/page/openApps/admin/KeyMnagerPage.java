package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KeyMnagerPage extends PageObject {
    private final String SUCCESS_MESSAGE = "Uploaded key successfully";
    @FindBy(id = "GenerateProdEnvKeys")
    private WebElement generateProdKeys;
    @FindBy(id = "RevokeProdEnvKeys")
    private WebElement revokeProdKeys;
    @FindBy(id = "DownloadProdEnvKey")
    private WebElement downloadProdKeys;
    @FindBy(id = "uploadProdEnvOmniaKeyInput")
    private WebElement uploadProdEnvOmniaKeyInput;
    @FindBy(id = "uploadProdEnvGsomKeyInput")
    private WebElement uploadProdEnvGsomKeyInput;
    @FindBy(className = "footer-message")
    private WebElement footerMessage;

    public KeyMnagerPage(WebDriver driver) {
        super(driver);
    }
    public boolean generate(EnviromentType enviromentType){
        int counter = 0;
        while (!isElementPresent(generateProdKeys) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        boolean res = false;
        if (enviromentType.equals(EnviromentType.PROD)){
            if(isElementPresent(revokeProdKeys)){
                revokeProdKeys.click();
                wait(WAIT);
            }
            if(isElementPresent(generateProdKeys))
                generateProdKeys.click();
            counter = 0;
            while (!isElementPresent(revokeProdKeys) && counter < 5){
                wait(WAIT);
                counter ++;
            }
            res =  revokeProdKeys.getText().contains("Revoke");
        }else {

        }
        return res;
    }
    public void downloadKey(EnviromentType enviromentType){
        int counter = 0;
        while (!isElementPresent(downloadProdKeys) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        if (enviromentType.equals(EnviromentType.PROD)){
            downloadProdKeys.click();
        }else {

        }
    }
    public boolean uploadOmnia(EnviromentType enviromentType,String filePath){
        boolean res = false;
        int counter = 0;
        while (!isElementPresent(uploadProdEnvOmniaKeyInput) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdEnvOmniaKeyInput.sendKeys(filePath);
            res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        }else {

        }
        return res;
    }
    public boolean uploadGSOM(EnviromentType enviromentType,String filePath){
        boolean res = false;
        int counter = 0;
        while (!isElementPresent(uploadProdEnvGsomKeyInput) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdEnvGsomKeyInput.sendKeys(filePath);
            res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        }else {

        }
        return res;
    }
}
