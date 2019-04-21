package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KeyManagement extends PageObject {
    @FindBy(id = "downloadProdOmniaSignedKeyButton")
    private WebElement downloadProdOmniaSignedKeyButton;
    @FindBy(id = "downloadProdGsomSignedKeyButton")
    private WebElement downloadProdGsomSignedKeyButton;
    @FindBy(id = "downloadTestOmniaProdSignedKeyButton")
    private WebElement downloadTestOmniaProdSignedKeyButton;
    @FindBy(id = "downloadTestGsomSignedKeyButton")
    private WebElement downloadTestGsomSignedKeyButton;
    @FindBy(id = "deploySignedKeysButtonProd")
    private WebElement deploySignedKeysButtonProd;
    @FindBy(id = "deploySignedKeysButtonTest")
    private WebElement deploySignedKeysButtonTest;
    @FindBy(id = "keysDeployButtonSave")
    private WebElement keysDeployButtonSave;


    public KeyManagement(WebDriver driver) {
        super(driver);

    }
    public void downloadProdOmniaSignedKeyButton(){
        downloadProdOmniaSignedKeyButton.click();
    }

    public void downloadProdGsomSignedKeyButton(){
        downloadProdGsomSignedKeyButton.click();
    }

    public void downloadTestOmniaSignedKeyButton(){
        downloadTestOmniaProdSignedKeyButton.click();
    }

    public void downloadTestGsomSignedKeyButton(EnviromentType enviromentType){
        if (enviromentType.equals(EnviromentType.PROD)){
            downloadProdGsomSignedKeyButton.click();
        }else
        downloadTestGsomSignedKeyButton.click();
    }

    public void deploySignedKeys(EnviromentType enviromentType){
        if (EnviromentType.PROD.equals(enviromentType)){
            deploySignedKeysButtonProd.click();
        }else {
            deploySignedKeysButtonTest.click();
        }
        wait(WAIT);
        keysDeployButtonSave.click();
    }

}
