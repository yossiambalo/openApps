package com.odysii.functional;
import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class AddUserTest extends TestBase {
    public final static String ADMIN_USER_NAME_ROI = "roi.avital.odysii@gmail.com";
    public final static String ADMIN_USER_PASS_ROI = "Aa123456789";


    AddUser addUser;
    String fillEmailAddress = "A0A21114d1w5@odysii.co.il";
    String fillEmailAddress2 = "BB12024d11w5@odysii.com";
    String fillEmailAddress3 = "CC312014wd15@odysii.org.il";
    String fillEmailAddress4 = "kjl21hldd1k@odys0ii.com";


    @Test
    public void _001_add_new_user() {
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME_ROI, ADMIN_USER_PASS_ROI, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
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
        addUser.addNewUser("Ori","ll@oop",UseRole.RETAILER_DEVELOPER_ALL,false,""); // Index of org override is from 0 to 9!!
        WebElement actualValue = driver.findElement(By.id("editUserButtonSave"));
        boolean x = actualValue.isEnabled();
        Assert.assertFalse(x);
        wait(WAIT);
        addUser.backToUsersPage();

    }

   @Test
    public void _007_create_new_user_with_org_override_CONFIRM_message(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUserWithOrgOverride("Sasha",fillEmailAddress2,3,UseRole.RETAILER_DEVELOPER_ALL,true,UserDelegations.IMPULSE_ADMIN,true);
        wait(WAIT);
        boolean actualValue = isElementExist(By.id("newUserButton"));
        Assert.assertTrue(actualValue);

    }

    @Test
    public void _008_create_new_user_with_org_override_CANCEL_message(){
        UsersPage usersPage = adminPage.getUsersPage();
        usersPage.getAddNewUserPage();
        addUser.addNewUserWithOrgOverride("Sasha",fillEmailAddress2,3,UseRole.RETAILER_DEVELOPER_ALL,true,UserDelegations.IMPULSE_ADMIN,false);
        wait(WAIT);
        boolean actualValue = isElementExist(By.id("BackNavigationButton"));
        Assert.assertTrue(actualValue);
        addUser.backToUsersPage();
    }

    @Test
    public void _009_new_user_without_retailer_and_org_id(){
        UsersPage usersPage = adminPage.getUsersPage();
        addUser = usersPage.getAddNewUserPage();
        addUser.addNewUser("roi", fillEmailAddress3, UseRole.PARTY_DEVELOPER_MARKETING_ALL,true,UserDelegations.OPENAPPS_ADMIN);
        wait(WAIT);
        usersPage.serachFileld(fillEmailAddress3);
//        WebElement searchField = driver.findElement(By.id("searchInput"));
//        searchField.sendKeys(fillEmailAddress3);
        usersPage.ListItems();
        wait(5000);
        String expectedValue = "Retailer:";
        String actualValue =  driver.findElement(By.xpath("/html/body/open-apps/div[2]/div/div/div/div[2]/ng-component/div[2]/div[1]/div[1]/div/div[2]/div[3]")).getText();
        Assert.assertEquals(expectedValue,actualValue);
        addUser.backToUsersPage();

    }

    @Test
    public void _010_empty_retailer_on_main_page_of_users_after_creation(){
        UsersPage usersPage = adminPage.getUsersPage();
        addUser = usersPage.getAddNewUserPage();
        addUser.addNewUser("Omari",fillEmailAddress4,UseRole.RETAILER_DEVELOPER_ENCORE_EXPERIENCE,true,UserDelegations.ODYSII);
        wait(3000);
        String actualValue = driver.findElement(By.className("ml--2")).getText();
        String expectedValue = "";
        Assert.assertEquals(actualValue,expectedValue);

    }

    //@Test
    public void _011_empty_organization_on_main_page_of_users_after_creation(){


    }

    @AfterClass
    public void deleteUser(){

        boolean res2 = deleteUser(fillEmailAddress2);
        Assert.assertFalse(res2,"Failed to delete user");

        wait(WAIT);

        boolean res = deleteUser(fillEmailAddress);
        Assert.assertTrue(res, "Failed to delete user");

        wait(WAIT);

        boolean res3 = deleteUser(fillEmailAddress3);
        Assert.assertFalse(res3, "Failed to delete user");

        wait(WAIT);

        boolean res4 = deleteUser(fillEmailAddress4);
        Assert.assertFalse(res4,"Failed to delete user");

    }

}
