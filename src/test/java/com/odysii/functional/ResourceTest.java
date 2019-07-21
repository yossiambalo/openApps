package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.AdminResource;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.DevResource;
import com.odysii.selenium.page.util.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ResourceTest extends TestBase {

    private AdminResource adminResource;
    final String KEY_FILE_LOCATION = System.getProperty("user.home")+"\\Downloads";
    @BeforeClass
    public void prepare(){
        category = "DeveloperResource";
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
    }
    @Test
    public void _001_valid_admin_add_new_resource_for_dev(){
        String resourceName = "Yossi";
        adminResource = adminPage.getResourcePage();
        adminResource.addNewResource(resourceName,"It is test description", PermissionCategoryType.DEVELOPER);
        wait(5000);
        Assert.assertTrue(adminResource.isResourceExist(resourceName));

        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        Set<String> resources = new HashSet<>();
        resources.add(resourceName);
        editUser.addResourcesForUser(resources);
        devUser = (DevHomePage)user.login(DEV_USER_NAME,DEV_USER_PASS,UserType.DEVELOPER);
        DevResource devResource = devUser.getResourcePage();
        Assert.assertTrue(devResource.isResourceExist(resourceName));

    }
    @Test
    public void _001_valid_admin_edit_resource(){
        if (!user.isUserLoggedIn(ADMIN_USER_NAME)){
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        }
        String resourceName = "Yossi";
        adminResource = adminPage.getResourcePage();
        adminResource.editResource(resourceName);
        wait(5000);
        Assert.assertTrue(adminResource.isResourceExist(resourceName));
    }
    @Test
    public void _002_valid_admin_download(){
        if (!user.isUserLoggedIn(ADMIN_USER_NAME)){
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        }
        adminResource = adminPage.getResourcePage();
        adminResource.download();
        wait(WAIT);
        FileHandler fileHandler = new FileHandler();
        Assert.assertTrue(fileHandler.isFileExist(KEY_FILE_LOCATION+ File.separator+"TH.zip"));
        fileHandler.deleteFile(KEY_FILE_LOCATION+ File.separator+"TH.zip");
    }
    @AfterClass
    public void cleanResource(){
        if (!user.isUserLoggedIn(ADMIN_USER_NAME)){
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        }
        adminResource = adminPage.getResourcePage();
        Assert.assertTrue(adminResource.deleteLastResource(),"Failed to delete adminResource!");
        driver.close();
    }
}
