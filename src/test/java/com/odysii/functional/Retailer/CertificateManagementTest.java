package com.odysii.functional.Retailer;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.KeyMnagerPage;
import com.odysii.selenium.page.openApps.admin.RetailersPage;
import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
//import com.odysii.selenium.page.openApps.admin.helper.RetailerName;
import com.odysii.selenium.page.openApps.admin.helper.RetailerName;
import com.odysii.selenium.page.openApps.retailer.KeyManagement;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import com.odysii.selenium.page.util.FileHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

public class CertificateManagementTest extends TestBase {
    KeyMnagerPage keyMnagerPage = null;
    AdminPage adminPage;
    RetailersPage retailersPage;
    private String siteAreaCheckBoxTagName = "i";
    final String KEY_FILE_LOCATION = System.getProperty("user.home")+"\\Downloads";
    FileHandler fileHandler;


    @BeforeClass

    public void prepare(){
        fileHandler = new FileHandler();
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
    }
    @Test
    public void _001_generate_download_upload_deploy_GSOM_PROD_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.PROD);
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        wait(WAIT);
        keyMnagerPage.uploadGSOM(EnviromentType.PROD,fileHandler.getRandomFileFromDir(new File(KEY_FILE_LOCATION)).toString());
        wait(WAIT);
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadProdGsomSignedKeyButton();
        keyManagement.deploySignedKeys(EnviromentType.PROD);
        Assert.assertTrue(isElementExist(By.id("siteSelectionAccordion")));

    }

    @Test
    public void _002_generate_download_upload_deploy_GSOM_TEST_E2E(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.downloadKey(EnviromentType.TEST);
        wait(WAIT);
        keyMnagerPage.uploadGSOM(EnviromentType.TEST,fileHandler.getRandomFileFromDir(new File(KEY_FILE_LOCATION)).toString());
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadTestGsomSignedKeyButton(EnviromentType.TEST);
        keyManagement.deploySignedKeys(EnviromentType.TEST);
        // Waiting for IDs to checkbox of deploy
    }

    @Test
    public void _003_generate_download_upload_deploy_OMNIA_PROD_E2E(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.PROD);
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        wait(WAIT);
        keyMnagerPage.uploadOmnia(EnviromentType.PROD,fileHandler.getRandomFileFromDir(new File(KEY_FILE_LOCATION)).toString());
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadProdOmniaSignedKeyButton();
        keyManagement.deploySignedKeys(EnviromentType.PROD);
        // Waiting for IDs to checkbox of deploy
    }

    @Test
    public void _004_generate_download_upload_deploy_OMNIA_TEST_E2E(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.downloadKey(EnviromentType.TEST);
        wait(WAIT);
        keyMnagerPage.uploadOmnia(EnviromentType.TEST,fileHandler.getRandomFileFromDir(new File(KEY_FILE_LOCATION)).toString());
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadTestOmniaSignedKeyButton();
        keyManagement.deploySignedKeys(EnviromentType.TEST);
        // Waiting for IDs to checkbox of deploy

    }

    @Test
    public void _005_deploy_is_disable_for_retailer_after_revoke(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.revoke(EnviromentType.TEST);
        user.logout();
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        retailerHomePage.getKeysMGMT();
        boolean expectedValue = driver.findElement(By.id("deploySignedKeysButtonTest")).isEnabled();
        Assert.assertFalse(expectedValue);

    }

    @Test
    public void _006_no_key_to_retailer_without_upload(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        user.logout();
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        retailerHomePage.getKeysMGMT();
        boolean expectedValue = driver.findElement(By.id("deploySignedKeysButtonTest")).isEnabled();
        Assert.assertFalse(expectedValue);

    }
    // Verify that if admin was generated key and download only (without upload signed key) retailer didn't get a key
    //STP: Certificate_Management-1-6
    @Test
    public void _007_no_key_to_retailer_after_download_without_upload(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.downloadKey(EnviromentType.TEST);
        user.logout();
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        retailerHomePage.getKeysMGMT();
        boolean expectedValue = driver.findElement(By.id("deploySignedKeysButtonTest")).isEnabled();
        Assert.assertFalse(expectedValue);

    }
    //Verify that after click on "Revoke" - retailer should not be able to download signed key and deploy (grade out)
    //Certificate_Management-1-8
    @Test
    public void _008_after_revoke_all_retailer_options_disable(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.downloadKey(EnviromentType.TEST);
        user.logout();
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        retailerHomePage.getKeysMGMT();
        boolean expectedValue = driver.findElement(By.id("downloadTestOmniaProdSignedKeyButton")).isEnabled();
        Assert.assertFalse(expectedValue);
        boolean expectedValue2 = driver.findElement(By.id("deploySignedKeysButtonTest")).isEnabled();
        Assert.assertFalse(expectedValue2);



    }


    @AfterMethod
    public void deleteKeyFile(){
        fileHandler.deleteFolderContent(new File(KEY_FILE_LOCATION));

    }

}
