package com.odysii.functional.Retailer;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.KeyMnagerPage;
import com.odysii.selenium.page.openApps.admin.RetailersPage;
import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
//import com.odysii.selenium.page.openApps.admin.helper.RetailerName;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CertificateManagement extends TestBase {
    KeyMnagerPage keyMnagerPage = null;
    AdminPage adminPage;
    RetailersPage retailersPage;

    @BeforeClass
    public void prepare(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS,UserType.ADMIN);
    }
    @Test
    public void _001_generate_download_upload_deploy_GSOM_E2E(){
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerType.SHELL);
        KeyMnagerPage keyMnagerPage = new KeyMnagerPage(driver);
        keyMnagerPage.generate(EnviromentType.PROD);
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        keyMnagerPage.uploadGSOM(EnviromentType.PROD,"C:\\Git_repository\\openApps\\src\\main\\resources\\content\\UploadKeyGSOM.txt");

//        Boolean actualValue = driver.findElement(By.xpath(//*[contains(text(), '')]);
//                Assert.assertEquals(expectedValue, );
    }
}
