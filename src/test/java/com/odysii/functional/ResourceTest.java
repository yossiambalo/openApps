package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.Resource;
import com.odysii.selenium.page.openApps.ResourceType;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.AdminResource;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResourceTest extends TestBase {

    private Resource adminResource;
    @BeforeClass
    public void prepare(){
        category = "DeveloperResource";
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
    }
    @Test
    public void _001_valid_admin_add_new_resource_for_dev(){
        String resourceName = "Yossi";
        adminResource = adminPage.getResourcePage(ResourceType.ADMIN_RESOURCE);
        adminResource.addNewResource("Yossi","It is test description", PermissionCategoryType.DEVELOPER);
        wait(5000);
        List<WebElement> resourceList = driver.findElements(By.className("card-body"));
        String lastResourceName = resourceList.get((resourceList.size() - 1)).findElement(By.className("col-md-3")).getText();
        Assert.assertEquals(lastResourceName,resourceName);

        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        Set<String> resources = new HashSet<>();
        resources.add("Yossi");
        editUser.addResourcesForUser(resources);
        //devUser = (DevHomePage)user.login(DEV_USER_NAME,DEV_USER_PASS,UserType.DEVELOPER);
    }
    @AfterMethod
    public void cleanResource(){
        if (!user.isUserLoggedIn(ADMIN_USER_NAME)){
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        }
        adminResource = adminPage.getResourcePage(ResourceType.ADMIN_RESOURCE);
        Assert.assertTrue(adminResource.deleteLastResource(),"Failed to delete adminResource!");
    }
}
