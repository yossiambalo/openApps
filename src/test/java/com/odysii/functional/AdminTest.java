package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AdminTest extends TestBase {

    DevHomePage devHomePage;
    @BeforeClass
    public void login(){
        user = new User(driver);
        devHomePage = (DevHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
    }
    @Test(priority = 7)
    public void _007_valid_edit_users_in_admin_console(){
        AdminPage devUser = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = devUser.getUsersPage();
        usersPage.editUser("DEVELOPER A");
    }
}
