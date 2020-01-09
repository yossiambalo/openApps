package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.io.File;
import java.util.List;

public class KeyMnagerPage extends PageObject {
    private final String SUCCESS_MESSAGE = "successfully";
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
    @FindBy(id = "sign_prod_input")
    private WebElement singProd;
    @FindBy(id = "sign_test_input")
    private WebElement signTest;
    @FindBy(xpath = ("//span[contains(text(), 'Sign failed, reason: Server error, please make sure package contains a valid manifest file.')]"))
    private WebElement errorMessageSignService;



//    @FindBy(css =".my-3 > input")
//    private List<WebElement> uploadZipFile;

//    @FindBy (xpath = "//button[contains(text(), 'Test Sign')]")
//    private WebElement uploadZipFile;



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
        isElementPresent(footerMessage);
        wait(3000);
        res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        return res;
    }
    public void deployToAllKeyManage(){
        isElementPresent(siteSelectionAccordion.findElements(By.xpath("nav-item")).get(0));

    }

    public void revokeProd(){
        revokeProdKeys.click();

    }

    public void revokeTest(){
        revokeTestKeys.click();
    }

//    public void uploadZip(String nameOfZip, int index){
//        uploadZipFile.get(index).sendKeys(getFile("code\\" + nameOfZip));
//
//    }

    public void uploadZipForSign(EnviromentType enviromentType, String nameOfZip){
        if (enviromentType.equals(EnviromentType.TEST)){
            signTest.sendKeys(getFile("code\\" + nameOfZip));
        }
        else
            singProd.sendKeys(getFile("code\\" + nameOfZip));
    }

    public boolean isFileDownloaded(String downloadPath, String fileName) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        for (int i = 0; i < dirContents.length; i++) {
            if (dirContents[i].getName().contains(fileName)) {
                dirContents[i].delete();
                return true;
            }

        }

        return false;

    }

    public String getErrorMsg(){
        String x = errorMessageSignService.getText();

        return x;
    }

}
