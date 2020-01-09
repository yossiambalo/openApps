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
import com.odysii.utils.FileHandler;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class ResourceTest extends TestBase {

    private AdminResource adminResource;
    final String KEY_FILE_LOCATION = System.getProperty("user.home")+"\\Downloads";
    private final String RESOURCE_NAME = "Resource name";
    private final String NEW_RESOURCE_NAME = "New resource name";

    @BeforeClass // Todo: clean resources before run
    public void prepare(){
        category = "DeveloperResource";
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
    }

    @Test
    public void _001_valid_admin_add_new_resource_for_dev(){
        adminResource = adminPage.getResourcePage();
        adminResource.addNewResource(RESOURCE_NAME,"It is test description", PermissionCategoryType.DEVELOPER);
        wait(5000);
        Assert.assertTrue(adminResource.isResourceExist(RESOURCE_NAME));

        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        Set<String> resources = new HashSet<>();
        resources.add(RESOURCE_NAME);
        editUser.addResourcesForUser(resources);
        devUser = (DevHomePage)user.login(DEV_USER_NAME,DEV_USER_PASS,UserType.DEVELOPER);
        DevResource devResource = devUser.getResourcePage();
        Assert.assertTrue(devResource.isResourceExist(RESOURCE_NAME));

    }

    @Test
    public void _002_valid_admin_edit_resource(){
        if (!user.isUserLoggedIn(ADMIN_USER_NAME)){
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        }
        adminResource = adminPage.getResourcePage();
        adminResource.editResource(RESOURCE_NAME, NEW_RESOURCE_NAME,"New description name",PermissionCategoryType.DEVELOPER);
        wait(5000);
        Assert.assertTrue(adminResource.isResourceExist(NEW_RESOURCE_NAME));
    }

    @Test
    public void _003_valid_admin_download(){
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
