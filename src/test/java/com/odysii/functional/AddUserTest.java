package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.*;
import com.odysii.selenium.page.openApps.admin.helper.OrganizationType;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddUserTest extends TestBase {

    @Test
    public void _001_add_new_user(){
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        adminPage.getUsersPage();
        AddUser addUser = new AddUser(driver);
        addUser.addNewUser("roi","Roi@so",UseRole.CUSTOM_ROLE, OrganizationType.ODYSII_QA.getIndex());

    }
}
