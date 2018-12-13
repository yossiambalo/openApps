package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.helper.appDetails.RetailerType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AdminTest extends TestBase {
    AdminPage adminPage;
    @BeforeClass
    public void init(){
        category = "Admin Console";
    }
    @Test(priority = 1)
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
        UsersPage usersPage = adminPage.getUsersPage();
    }
}
