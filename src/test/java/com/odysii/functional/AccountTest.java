package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.helper.Retry;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.OrganizationType;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountTest extends TestBase {
    public static String DEV_USER_NAME2 = "auto.mazia2@gmail.com";

    @BeforeClass
    public void prepare(){
        //deleteAllApps();
        category = "Accounts";
    }

    @Test
    public void _001_verify_dev_user_with_role_1_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(1),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_1, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        //ToDo: Unmark assertion when links will available
        //Assert.assertTrue(devUser.isDashboardEnabled(), "Dashboard link not clickable!");
        Assert.assertTrue(devUser.isMyAppsEnabled(), "MyApps link not clickable!");
        //Assert.assertTrue(devUser.isTransactionHistoryEnabled(), "Transaction History link not clickable!");
        //Assert.assertTrue(devUser.isRevenueReportEnabled(), "Revenue Report link not clickable!");
        Assert.assertTrue(devUser.isSupportTicketEnabled(), "Support Ticket link not clickable!");
        //Assert.assertTrue(devUser.isPublicProfileEnabled(), "Publish Profile link not clickable!");
        //Assert.assertTrue(devUser.isEncoreEnabled(), "Encore link not clickable!");
        Assert.assertTrue(devUser.isAppStoreEnabled(), "App Store link not clickable!");
    }

    @Test(retryAnalyzer = Retry.class)
    public void _002_verify_dev_user_with_role_2_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(2),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_2, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        //ToDo: Unmark assertion when links will available
        //Assert.assertTrue(devUser.isPassportEnabled(), "Passport link not clickable!");
    }

    @Test
    public void _003_verify_dev_user_with_role_3_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(3),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_3, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        //Assert.assertTrue(devUser.isPassportEnabled(), "Passport link not clickable!");
        //Assert.assertTrue(devUser.isDashboardEnabled(), "Dashboard link not clickable!");
        Assert.assertTrue(devUser.isMyAppsEnabled(), "My Apps link not clickable!");
        //Assert.assertTrue(devUser.isTransactionHistoryEnabled(), "Transaction History link not clickable!");
        //Assert.assertTrue(devUser.isRevenueReportEnabled(), "Revenue Report link not clickable!");
        Assert.assertTrue(devUser.isSupportTicketEnabled(), "Support Ticket link not clickable!");
        //Assert.assertTrue(devUser.isPublicProfileEnabled(), "Publish Profile link not clickable!");
        Assert.assertTrue(devUser.isAppStoreEnabled(), "App Store link not clickable!");
    }

    @Test
    public void _004_verify_retailer_user_with_role_4_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(4),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_4, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        Assert.assertTrue(devUser.isLibraryEnabled(), "Library link not clickable!");
        //Assert.assertTrue(devUser.isDashboardEnabled(), "Dashboard link not clickable!");
        Assert.assertTrue(devUser.isMyAppsEnabled(), "My Apps link not clickable!");
        //Assert.assertTrue(devUser.isTransactionHistoryEnabled(), "Transaction History link not clickable!");
        //Assert.assertTrue(devUser.isRevenueReportEnabled(), "Revenue Report link not clickable!");
        Assert.assertTrue(devUser.isSupportTicketEnabled(), "Support Ticket link not clickable!");
        //Assert.assertTrue(devUser.isPublicProfileEnabled(), "Publish Profile link not clickable!");
        //Assert.assertTrue(devUser.isEncoreEnabled(), "Encore link not clickable!");
        Assert.assertTrue(devUser.isAppStoreEnabled(), "App Store link not clickable!");
    }

    @Test
    public void _005_verify_retailer_user_with_role_5_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(5),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_5, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        //ToDo: Unmark assertion when links will avaiable
        //Assert.assertTrue(devUser.isPassportEnabled(), "Passport link not clickable!");
    }

    @Test
    public void _006_verify_retailer_user_with_role_6_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(6),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_6, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        //Assert.assertTrue(devUser.isPassportEnabled(), "Passport link not clickable!");
        Assert.assertTrue(devUser.isLibraryEnabled(), "Library link not clickable!");
        //Assert.assertTrue(devUser.isDashboardEnabled(), "");
        Assert.assertTrue(devUser.isMyAppsEnabled(), "My Apps link not clickable!");
        //Assert.assertTrue(devUser.isTransactionHistoryEnabled(), "Transaction History link not clickable!");
        //Assert.assertTrue(devUser.isRevenueReportEnabled(), "Revenue Report link not clickable!");
        Assert.assertTrue(devUser.isSupportTicketEnabled(), "Support Ticket link not clickable!");
        //Assert.assertTrue(devUser.isPublicProfileEnabled(), "Publish Profile link not clickable!");
        //Assert.assertTrue(devUser.isEncoreEnabled(), "Encore link not clickable!");
        Assert.assertTrue(devUser.isAppStoreEnabled(), "App Store link not clickable!");
    }

    @Test
    public void _007_verify_retailer_user_with_role_7_has_only_its_permissions() {
        //Assert.assertTrue(updateUser(7),"Failed to update user role!");
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_7, null);
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        Assert.assertTrue(devUser.isAppStoreEnabled(), "App Store link not clickable!");
        Assert.assertTrue(devUser.isLibraryEnabled(), "Library link not clickable!");
        Assert.assertTrue(devUser.isCampaignEnabled(), "Campaign link not clickable!");
        Assert.assertTrue(devUser.isSchedulingEnabled(), "Scheduling link not clickable!");
        Assert.assertTrue(devUser.isKeyManagementEnabled(), "Key Management link not clickable!");
        //Assert.assertTrue(devUser.isAppStoreEnabled(), "App Store link not clickable!");
    }

    @Test//(retryAnalyzer = Retry.class)
    public void _008_verify_admin_user_with_role_8_has_only_its_permissions() {
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_8, null);
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        Assert.assertTrue(adminPage.isSupportTicketsEnabled(), "Support Ticket link not clickable!");
        Assert.assertTrue(adminPage.isRetailersEnabled(), "Retailer link not clickable!");
        //Assert.assertTrue(adminPage.isStatisticsEnabled(), "Statistics link not clickable!");
        Assert.assertTrue(adminPage.isUsersEnabled(), "Users link not clickable!");
    }
    @Test
    public void _009_verify_devs_in_same_organization_see_each_others_app() {
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_3, null, OrganizationType.EXXONMOBILI);
        editUser = usersPage.getUser(DEV_USER_NAME2);
        editUser.edit(RoleType.ROLE_3, null, OrganizationType.EXXONMOBILI);
        //login as developer A and init expected apps
        user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        int expectedApps = driver.findElements(By.className(APP_CLASS_NAME)).size()+1;
        prepareTest("app_details.properties", ApplicationStatus.PRESUBMITTED);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(expectedApps,actualApps,"Actual apps of developer A are not as expected!");
        //login as developer B and valid apps are as expected
        user.login(DEV_USER_NAME2, DEV_USER_PASS, UserType.DEVELOPER);
        actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(expectedApps,actualApps,"Actual apps of developer B are not as expected!");
    }
}
