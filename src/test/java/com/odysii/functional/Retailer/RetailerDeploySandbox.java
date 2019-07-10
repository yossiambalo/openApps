package com.odysii.functional.Retailer;
import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.retailer.AreaType;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import com.odysii.selenium.page.openApps.retailer.Scheduling;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetailerDeploySandbox extends TestBase {

    String RETAILER_USER_NAME_SANDBOX = "roi.avital.odysii@gmail.com";
    String RETAILER_USER_PASS_SANDBOX = "aaaAAA123";

    @BeforeClass
    public void prepare(){

        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME_SANDBOX,RETAILER_USER_PASS_SANDBOX, UserType.RETAILER);
//        prepareTest("app_details.properties", ApplicationStatus.SUBMITTED);
//        prepareTest("app_details.properties", ApplicationStatus.LIVE);

    }

    @Test
    public void _001_scheduling_deploy_to_dispenser(){
        RetailerHomePage retailerHomePage = new RetailerHomePage(driver);
        retailerHomePage.getScheduling();
        Scheduling scheduling = new Scheduling(driver);
        scheduling.deployToAll(0);
        String expectedValue = "Campaign Deploy request was dispatched.";
        wait(WAIT);
        String actualValue = driver.findElement(By.className("pb-3")).getText();
        Assert.assertEquals(actualValue,expectedValue);

        }

}
