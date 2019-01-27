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
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import com.odysii.selenium.page.openApps.retailer.KeyManagement;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import com.odysii.selenium.page.openApps.retailer.Scheduling;
import com.odysii.selenium.page.util.FileHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CertificateManagement extends TestBase {
    KeyMnagerPage keyMnagerPage = null;
    AdminPage adminPage;
    RetailersPage retailersPage;
    private String siteAreaCheckBoxTagName = "i";
    final String GENERATED_KEY_FILE_PATH = System.getProperty("user.home")+"\\Downloads\\cert_pub_retailer_id_2.txt";
    @BeforeClass
    public void prepare(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
    }
    //@Test
    public void _001_generate_download_upload_deploy_GSOM_PROD_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.PROD);
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        wait(WAIT);
        keyMnagerPage.uploadGSOM(EnviromentType.PROD,GENERATED_KEY_FILE_PATH);
        wait(WAIT);
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadProdGsomSignedKeyButton();
        keyManagement.deploySignedKeysButtonPro();
        driver.findElement(By.id("siteSelectionAccordion")).findElements(By.className("nav-item")).get(1);
        // Waiting for IDs to checkbox of deploy

    }

    @Test
    public void _002_generate_download_upload_deploy_GSOM_TEST_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.ODYSII);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.downloadKey(EnviromentType.TEST);
        wait(WAIT);
        keyMnagerPage.uploadGSOM(EnviromentType.TEST,GENERATED_KEY_FILE_PATH);
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadTestGsomSignedKeyButton();
        keyManagement.deploySignedKeysButtonTest();
        // Waiting for IDs to checkbox of deploy
    }

    @Test
    public void _003_generate_download_upload_deploy_OMNIA_PROD_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SPRINT_MART);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.PROD);
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        wait(WAIT);
        keyMnagerPage.uploadOmnia(EnviromentType.PROD,GENERATED_KEY_FILE_PATH);
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadProdOmniaSignedKeyButton();
        keyManagement.deploySignedKeysButtonPro();
        // Waiting for IDs to checkbox of deploy
    }

    @Test
    public void _004_generate_download_upload_deploy_OMNIA_TEST_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.EXXONMOBIL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.TEST);
        keyMnagerPage.downloadKey(EnviromentType.TEST);
        wait(WAIT);
        keyMnagerPage.uploadOmnia(EnviromentType.TEST,GENERATED_KEY_FILE_PATH);
        user.logout();
        RetailerHomePage retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        KeyManagement keyManagement = retailerHomePage.getKeysMGMT();
        keyManagement.downloadTestOmniaSignedKeyButton();
        keyManagement.deploySignedKeysButtonPro();
        keyManagement.deploySignedKeysButtonTest();
        // Waiting for IDs to checkbox of deploy

    }

    @AfterClass
    public void clean(){
        FileHandler fileHandler = new FileHandler();
        fileHandler.deleteFile(GENERATED_KEY_FILE_PATH);

    }
}
