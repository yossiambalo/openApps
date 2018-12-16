package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.util.FileHandler;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class RetailersPage extends PageObject {

    @FindBy(className = "admin-retailer-row")
    private List<WebElement> retailerDivs;
    private final String SUCCESS_MESAGE = "Revoke";
    public RetailersPage(WebDriver driver) {
        super(driver);
    }
    public void getEditPage(String retailer){
        boolean flag = false;
        isElementPresent(retailerDivs.get(0));
        for (int i = 0; i < retailerDivs.size()-1; i++){
            if(retailerDivs.get(i).findElement(By.className("child_center")).getText().toLowerCase().equals(retailer.replaceAll("[\\s|\\u00A0]+", "").toLowerCase())){
                retailerDivs.get(i).findElement(By.className("block-item-menu-icon")).click();
                flag = true;
                webDriver.findElement(By.id("editRetailer"+(i+1))).click();
                break;
            }
        }
        if (!flag){
            throw new WebDriverException("Retailer with name: "+retailer+" not found, please make sure name is exist!");
        }
    }
    public boolean generate(EnviromentType enviromentType){
        int counter = 0;
        while (!isElementPresent(By.id("GenerateProdEnvKeys")) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        WebElement generateProdKeysElm = webDriver.findElement(By.id("GenerateProdEnvKeys"));
        boolean res = false;
        if (enviromentType.equals(EnviromentType.PROD)){
            generateProdKeysElm.click();
            counter = 0;
            while (!isElementPresent(By.id("RevokeProdEnvKeys")) && counter < 5){
                wait(WAIT);
                counter ++;
            }
            res =  webDriver.findElement(By.id("RevokeProdEnvKeys")).getText().contains(SUCCESS_MESAGE);
        }else {

        }
        return res;
    }
    public void downloadKey(EnviromentType enviromentType){
        int counter = 0;
        while (!isElementPresent(By.id("DownloadProdEnvKey")) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        WebElement downloadProdKeysElm = webDriver.findElement(By.id("DownloadProdEnvKey"));
        if (enviromentType.equals(EnviromentType.PROD)){
            downloadProdKeysElm.click();
        }else {

        }
    }
    public boolean uploadOmnia(EnviromentType enviromentType,String filePath){
        boolean res = false;
        int counter = 0;
        while (!isElementPresent(By.id("uploadProdEnvOmniaKeyInput")) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        WebElement uploadProdKeysElm = webDriver.findElement(By.id("uploadProdEnvOmniaKeyInput"));
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdKeysElm.sendKeys(filePath);
            res = webDriver.findElement(By.className("footer-message")).getText().contains("Uploaded key successfully");
        }else {

        }
        return res;
    }
    public boolean uploadGSOM(EnviromentType enviromentType,String filePath){
        boolean res = false;
        int counter = 0;
        while (!isElementPresent(By.id("uploadProdEnvGsomKeyInput")) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        WebElement uploadProdKeysElm = webDriver.findElement(By.id("uploadProdEnvGsomKeyInput"));
        if (enviromentType.equals(EnviromentType.PROD)){
            uploadProdKeysElm.sendKeys(filePath);
            res = webDriver.findElement(By.className("footer-message")).getText().contains("Uploaded key successfully");
        }else {

        }
        return res;
    }
}
