package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;

public class KeyMnagerPage extends PageObject {

    private final String SUCCESS_MESSAGE = "Uploaded key successfully";
    @FindBy(id = "GenerateProdEnvKeys")
    private WebElement generateProdKeys;
    @FindBy(id = "GenerateTestKeys")
    private WebElement generateTestKeys;
    @FindBy(id = "RevokeProdEnvKeys")
    private WebElement revokeProdKeys;
    @FindBy(id = "RevokeTestKeys")
    private WebElement revokeTestKeys;
    @FindBy(id = "DownloadProdEnvKey")
    private WebElement downloadProdKeys;
    @FindBy(id = "DownloadTestKey")
    private WebElement downloadTestKey;
    @FindBy(id = "uploadProdEnvOmniaKeyInput")
    private WebElement uploadProdEnvOmniaKeyInput;
    @FindBy(id = "uploadTestEnvOmniaProdKeyInput")
    private WebElement uploadTestEnvOmniaProdKeyInput;
    @FindBy(id = "uploadProdEnvGsomKeyInput")
    private WebElement uploadProdEnvGsomKeyInput;
    @FindBy(id = "uploadTestEnvGsomKeyInput")
    private WebElement uploadTestEnvGsomKeyInput;
    @FindBy(className = "footer-message")
    private WebElement footerMessage;
    @FindBy (id = "siteSelectionAccordion")
    private WebElement siteSelectionAccordion;


    public KeyMnagerPage(WebDriver driver) {
        super(driver);
    }
    public boolean generate(EnviromentType enviromentType) {

        boolean revokeText = false;
        if (enviromentType.equals(EnviromentType.PROD)) {
            if (isElementPresent(revokeProdKeys)) {
                revokeProdKeys.click();
                wait(3000);
            }

            if (isElementPresent(generateProdKeys)) {
                generateProdKeys.click();
                wait(WAIT);
                revokeText = revokeProdKeys.getText().contains("Revoke");
            }

        }
        else {
            if (isElementPresent(revokeTestKeys)) {
                revokeTestKeys.click();
                wait(3000);
            }

            if (isElementPresent(generateTestKeys)) {
                generateTestKeys.click();
                wait(WAIT);
                revokeText = revokeTestKeys.getText().contains("Revoke");
            }
        }


        return revokeText;
    }

    public void revoke(EnviromentType enviromentType){
        revokeTestKeys.click();
    }

    public void downloadKey(EnviromentType enviromentType){
        int counter = 0;

////        while (!isElementExist(By.id("downloadProdKeys")) && counter < 5){
////            wait(WAIT);
////            counter ++;
//        }

        if (enviromentType.equals(EnviromentType.PROD)){
            downloadProdKeys.click();
        }
        else {
            downloadTestKey.click();
        }
    }

    public boolean uploadOmnia(EnviromentType enviromentType,String filePath){
        boolean res = false;
        int counter = 0;
//        while (!isElementExist(By.id("uploadProdEnvOmniaKeyInput")) && counter < 5){
//            wait(WAIT);
//            counter ++;
//        }
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdEnvOmniaKeyInput.sendKeys(filePath);
        }else {
            uploadTestEnvOmniaProdKeyInput.sendKeys(filePath);
        }
        wait(3000);
        res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        return res;
    }

    public boolean uploadGSOM(EnviromentType enviromentType,String filePath){
        boolean res = false;
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdEnvGsomKeyInput.sendKeys(filePath);
        }else {
            uploadTestEnvGsomKeyInput.sendKeys(filePath);
        }
        wait(3000);
        res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        return res;
    }
    public void deployToAllKeyManage(){
        isElementPresent(siteSelectionAccordion.findElements(By.xpath("nav-item")).get(0));

    }

}
