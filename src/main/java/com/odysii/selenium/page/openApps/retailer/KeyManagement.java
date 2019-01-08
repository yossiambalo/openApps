package com.odysii.selenium.page.openApps.retailer;

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

    public void downloadTestGsomSignedKeyButton(){
        downloadTestGsomSignedKeyButton.click();
    }

    public void deploySignedKeysButtonPro(){
        deploySignedKeysButtonProd.click();
    }

    public void deploySignedKeysButtonTest(){
        deploySignedKeysButtonTest.click();
    }

}
