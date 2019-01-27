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
    @FindBy (id = "siteSelectionAccordion")
    private WebElement siteSelectionAccordion;

    public KeyMnagerPage(WebDriver driver) {
        super(driver);
    }
    public boolean generate(EnviromentType enviromentType) {

        if (isElementExist(By.id("revokeProdKeys")) ) {
            revokeProdKeys.click();
        }
        if (isElementExist(By.id("generateProdKeys"))){
            generateProdKeys.click();
        }
        wait(WAIT);
        return revokeProdKeys.getText().contains("Revoke");
    }
    public void downloadKey(EnviromentType enviromentType){
        int counter = 0;
////        while (!isElementExist(By.id("downloadProdKeys")) && counter < 5){
////            wait(WAIT);
////            counter ++;
//        }
        if (enviromentType.equals(EnviromentType.PROD)){
            downloadProdKeys.click();
        }else {

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
            try {
                uploadProdEnvOmniaKeyInput.sendKeys(filePath);
            }catch (WebDriverException e){
                System.out.println(e.getMessage());
            }
            res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        }else {

        }
        return res;
    }
    public boolean uploadGSOM(EnviromentType enviromentType,String filePath){
        boolean res = false;
        int counter = 0;
//        while (!isElementExist(By.id("uploadProdEnvGsomKeyInput")) && counter < 5){
//            wait(WAIT);
//            counter ++;
//        }
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdEnvGsomKeyInput.sendKeys(filePath);
            res = footerMessage.getText().contains(SUCCESS_MESSAGE);
        }else {

        }
        return res;
    }
    public void deployToAllKeyManage(){
        isElementPresent(siteSelectionAccordion.findElements(By.xpath("nav-item")).get(0));


    }

}
