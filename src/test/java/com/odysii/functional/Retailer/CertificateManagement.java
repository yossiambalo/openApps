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
import com.odysii.selenium.page.util.FileHandler;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CertificateManagement extends TestBase {
    KeyMnagerPage keyMnagerPage = null;
    AdminPage adminPage;
    RetailersPage retailersPage;
    final String GENERATED_KEY_FILE_PATH = System.getProperty("user.home")+"\\Downloads\\cert_pub_retailer_id_2.txt";
    @BeforeClass
    public void prepare(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
    }
    @Test
    public void _001_generate_download_upload_deploy_GSOM_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.PROD);
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        wait(WAIT);
        keyMnagerPage.uploadGSOM(EnviromentType.PROD,GENERATED_KEY_FILE_PATH);
        wait(WAIT);
        user.logout();
//        System.out.println("sss");
    }
    @AfterClass
    public void clean(){
        FileHandler fileHandler = new FileHandler();
        fileHandler.deleteFile(GENERATED_KEY_FILE_PATH);
    }
}
