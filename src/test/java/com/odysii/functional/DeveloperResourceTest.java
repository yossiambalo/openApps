package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.Resource;
import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DeveloperResourceTest extends TestBase {

    private Resource resource;
    @BeforeClass
    public void prepare(){
        category = "DeveloperResource";
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
    }
    @Test
    public void _001_valid_admin_add_new_resource_for_dev(){
        String resourceName = "Yossi";
        resource = adminPage.getResourcePage();
        resource.addNewResource("Yossi","It is test description", PermissionCategoryType.DEVELOPER);
        wait(5000);
        List<WebElement> resourceList = driver.findElements(By.className("card-body"));
        String lastResourceName = resourceList.get((resourceList.size() - 1)).findElement(By.className("col-md-3")).getText();
        Assert.assertEquals(lastResourceName,resourceName);
    }
    @AfterMethod
    public void cleanResource(){
        if (!user.isUserLoggedIn(ADMIN_USER_NAME)){
            adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        }
        resource = adminPage.getResourcePage();
        Assert.assertTrue(resource.deleteLastResource(),"Failed to delete resource!");
    }
}
