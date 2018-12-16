package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.RetailersPage;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import com.odysii.selenium.page.util.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AdminTest extends TestBase {
    AdminPage adminPage;
    RetailersPage retailersPage;
    FileHandler fileHandler = null;
    final String GENERATED_KEY_FILE_PATH = System.getProperty("user.home")+"\\Downloads\\cert_pub_retailer_id_1.txt";
    @BeforeClass
    public void init(){
        category = "Admin Console";
    }
    //@Test(priority = 1)
    public void _001_valid_edit_users_in_admin_console(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        List<String> retailers = new ArrayList<>();
        retailers.add(RetailerType.SHELL);
        retailers.add(RetailerType.EXXON_MOBIL);
        usersPage.editUser("DEVELOPER A", RoleType.THIRD_PARTY,retailers);
        //TODO: add assertion when indicator will be ready by developers
        Assert.assertTrue(true);
    }
    @Test(priority = 2)
    public void _002_valid_generate_key(){
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        retailersPage = adminPage.getRetailersPage();
        retailersPage.getEditPage(RetailerType.EXXON_MOBIL);
        Assert.assertTrue(retailersPage.generate(EnviromentType.PROD));
    }
    @Test(priority = 3)
    public void _003_valid_generate_key(){
        if(!driver.getCurrentUrl().contains("manage-keys")){
            user = new User(driver);
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
            retailersPage = adminPage.getRetailersPage();
            retailersPage.getEditPage(RetailerType.EXXON_MOBIL);
        }
        retailersPage.downloadKey(EnviromentType.PROD);
        int counter = 0;
        fileHandler = new FileHandler();
        while (!fileHandler.isFileExist(GENERATED_KEY_FILE_PATH) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        Assert.assertTrue(fileHandler.isFileExist(GENERATED_KEY_FILE_PATH));
    }
    @Test(priority = 4)
    public void _004_valid_upload_key_omnia(){
        if(!driver.getCurrentUrl().contains("manage-keys")){
            user = new User(driver);
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
            retailersPage = adminPage.getRetailersPage();
            retailersPage.getEditPage(RetailerType.EXXON_MOBIL);
        }
        Assert.assertTrue(retailersPage.uploadOmnia(EnviromentType.PROD,GENERATED_KEY_FILE_PATH));
    }
    @Test(priority = 5)
    public void _005_valid_upload_key_gesom(){
        if(!driver.getCurrentUrl().contains("manage-keys")){
            user = new User(driver);
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
            retailersPage = adminPage.getRetailersPage();
            retailersPage.getEditPage(RetailerType.EXXON_MOBIL);
        }
        Assert.assertTrue(retailersPage.uploadGSOM(EnviromentType.PROD,GENERATED_KEY_FILE_PATH));
    }
    @AfterClass
    public void clean(){
        if (fileHandler != null){
            fileHandler.deleteFile(GENERATED_KEY_FILE_PATH);
        }
    }
}
