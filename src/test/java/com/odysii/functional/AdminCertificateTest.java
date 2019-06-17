package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.*;
import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.openApps.admin.helper.RetailerName;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import com.odysii.selenium.page.util.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AdminCertificateTest extends TestBase {
    AdminPage adminPage;
    RetailersPage retailersPage;
    FileHandler fileHandler = null;
    KeyMnagerPage keyMnagerPage = null;
    final String GENERATED_KEY_FILE_DIR = System.getProperty("user.home")+"\\Downloads\\";
    private final String CURRENT_PAGE_INDICATOR = "manage-keys";
    @BeforeClass
    public void init(){
        category = "Admin Certificate";
    }
    //@Test(priority = 1)
    public void _001_valid_edit_users_in_admin_console(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        List<String> retailers = new ArrayList<>();
        retailers.add(RetailerType.SHELL);
        retailers.add(RetailerType.EXXON_MOBIL);
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_1,retailers);
        //TODO: add assertion when indicator will be ready by developers
        Assert.assertTrue(true);
    }
    @Test(priority = 2)
    public void _002_valid_generate_key(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        keyMnagerPage = retailersPage.editRetailer(RetailerName.ODYSII);
        Assert.assertTrue(keyMnagerPage.generate(EnviromentType.PROD));
    }
    @Test(priority = 3)
    public void _003_valid_download_key(){
        if(!driver.getCurrentUrl().contains(CURRENT_PAGE_INDICATOR)){
            user = new User(driver);
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
            retailersPage = adminPage.getRetailersPage();
            retailersPage.editRetailer(RetailerName.SHELL);
        }
        keyMnagerPage.downloadKey(EnviromentType.PROD);
        int counter = 0;
        fileHandler = new FileHandler();
        while (!fileHandler.isFileExist(new File(GENERATED_KEY_FILE_DIR).listFiles()[0].toString()) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        Assert.assertTrue(fileHandler.isFileExist(new File(GENERATED_KEY_FILE_DIR).listFiles()[0].toString()));
    }
    @Test(priority = 4)
    public void _004_valid_upload_key_omnia(){
        if(!driver.getCurrentUrl().contains(CURRENT_PAGE_INDICATOR)){
            user = new User(driver);
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
            retailersPage = adminPage.getRetailersPage();
            retailersPage.editRetailer(RetailerName.SPRINT_MART);
        }
        Assert.assertTrue(keyMnagerPage.uploadOmnia(EnviromentType.PROD, new File(GENERATED_KEY_FILE_DIR).listFiles()[0].toString()));
    }
    @Test(priority = 5)
    public void _005_valid_upload_key_gesom(){
        if(!driver.getCurrentUrl().contains(CURRENT_PAGE_INDICATOR)){
            user = new User(driver);
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
            retailersPage = adminPage.getRetailersPage();
            retailersPage.editRetailer(RetailerName.SPRINT_MART);
        }
        Assert.assertTrue(keyMnagerPage.uploadGSOM(EnviromentType.PROD, new File(GENERATED_KEY_FILE_DIR).listFiles()[0].toString()));
    }
    @AfterClass
    public void clean(){
        if (fileHandler != null){
            fileHandler.deleteFile(new File(GENERATED_KEY_FILE_DIR).listFiles()[0].toString());
        }
    }
}
