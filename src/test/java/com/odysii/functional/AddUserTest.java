package com.odysii.functional;
import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AddUserTest extends TestBase {
    public final static String ADMIN_USER_NAME_ROI = "roi.avital.odysii@gmail.com";
    public final static String ADMIN_USER_PASS_ROI = "Aa123456789";


    AddUser addUser;
    String fillEmailAddress = "14newEmail@odysii.com";

    @Test
    public void _001_add_new_user() {
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME_ROI, ADMIN_USER_PASS_ROI, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
//      adminPage.getUsersPage();
        addUser = usersPage.getAddNewUserPage();
        addUser.addNewUser("roi", fillEmailAddress, UseRole.PARTY_DEVELOPER_MARKETING_ALL,true,UserDelegations.OPENAPPS_ADMIN);
        wait(WAIT);
        boolean actualValue = isElementExist(By.id("newUserButton"));
        Assert.assertTrue(actualValue);

    }

    @Test
    public void _002_cant_add_duplicate_email_address(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUser("QA for test 2",fillEmailAddress,UseRole.RETAILER_DEVELOPER_PASSPORT,true,UserDelegations.ODYSII);
        String actualValue = driver.findElement(By.id("editUserSuccessErrorMessage")).getText();
        String expectedValue = "An error occurred while trying to add new user. reason: email is already taken.";
        Assert.assertEquals(actualValue,expectedValue);
        wait(WAIT);
        addUser.backToUsersPage();

    }

    @Test
    public void _003_mandatory_fields_add_new_user_page_empty_name(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUser("","qa@wq",UseRole.PARTY_DEVELOPER_MARKETING_ALL,true,UserDelegations.OPENAPPS_ADMIN);
        WebElement actualValue = driver.findElement(By.id("editUserButtonSave"));
        boolean x = actualValue.isEnabled();
        Assert.assertFalse(x);
        wait(WAIT);
        addUser.backToUsersPage();

    }

    @Test
    public void _004_mandatory_fields_add_new_user_page_empty_email_address(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUser("Etgar","",UseRole.PARTY_DEVELOPER_MARKETING_ENCORE_EXPERIENCE, true, UserDelegations.EXXON_MOBIL);
        WebElement actualValue = driver.findElement(By.id("editUserButtonSave"));
        boolean x = actualValue.isEnabled();
        Assert.assertFalse(x);
        wait(WAIT);
        addUser.backToUsersPage();

    }

   @Test
    public void _005_mandatory_fields_add_new_user_page_empty_role(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUser("Tal","ww@ee","",true, UserDelegations.OPENAPPS_ADMIN);
        WebElement actualValue = driver.findElement(By.id("editUserButtonSave"));
        boolean x = actualValue.isEnabled();
        Assert.assertFalse(x);
        wait(WAIT);
        addUser.backToUsersPage();

    }

    @Test
    public void _006_mandatory_fields_add_new_user_page_empty_delegations(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUser("Ori","ll@oop",UseRole.RETAILER_DEVELOPER_ALL,false,"");
        WebElement actualValue = driver.findElement(By.id("editUserButtonSave"));
        boolean x = actualValue.isEnabled();
        Assert.assertFalse(x);
        wait(WAIT);
        addUser.backToUsersPage();

    }

    //@Test
    public void _007_create_new_user_with_org_override(){

    }

    //@Test
    public void _008_search_field_functionality_by_name(){

    }

    //@Test
    public void _009_new_user_without_retailer_and_org_id(){

    }

    //@Test
    public void _010_empty_org_on_main_page_of_users_after_creation(){

    }

    @AfterClass
    public void deleteUser(){
        boolean res = deleteUser(fillEmailAddress);
        Assert.assertTrue(res, "Failed to delete user");
        //Same like above - other way.
        // Assert.assertTrue(deleteUser(fillEmailAddress));

    }

}
