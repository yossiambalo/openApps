package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.helper.LinkText;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest extends TestBase {
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
        Assert.assertEquals(devUser.getDashboardText().toLowerCase(), LinkText.DASHBOARD.toLowerCase(), "");
        Assert.assertEquals(devUser.getMyAppsText().toLowerCase(), LinkText.MY_APPS.toLowerCase(), "");
        Assert.assertEquals(devUser.getTransactionHistoryText().toLowerCase(), LinkText.TRANSACTION_HISTORY.toLowerCase(), "");
        Assert.assertEquals(devUser.getRevenueReportText().toLowerCase(), LinkText.REVENUE_REPORT.toLowerCase(), "");
        Assert.assertEquals(devUser.getSupportTicketText().toLowerCase(), LinkText.SUPPORT_TICKETS.toLowerCase(), "");
        Assert.assertEquals(devUser.getPublicProfileText().toLowerCase(), LinkText.PUBLIC_PROFILE.toLowerCase(), "");
        Assert.assertEquals(devUser.getEncoreText().toLowerCase(), LinkText.ENCORE.toLowerCase(), "");
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(), LinkText.APP_STORE.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 8, "");
    }

    @Test
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
        Assert.assertEquals(devUser.getPassportText().toLowerCase(), LinkText.PASSPORT.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 1, "");
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
        Assert.assertEquals(devUser.getPassportText().toLowerCase(), LinkText.PASSPORT.toLowerCase(), "");
        Assert.assertEquals(devUser.getDashboardText().toLowerCase(), LinkText.DASHBOARD.toLowerCase(), "");
        Assert.assertEquals(devUser.getMyAppsText().toLowerCase(), LinkText.MY_APPS.toLowerCase(), "");
        Assert.assertEquals(devUser.getTransactionHistoryText().toLowerCase(), LinkText.TRANSACTION_HISTORY.toLowerCase(), "");
        Assert.assertEquals(devUser.getRevenueReportText().toLowerCase(), LinkText.REVENUE_REPORT.toLowerCase(), "");
        Assert.assertEquals(devUser.getSupportTicketText().toLowerCase(), LinkText.SUPPORT_TICKETS.toLowerCase(), "");
        Assert.assertEquals(devUser.getPublicProfileText().toLowerCase(), LinkText.PUBLIC_PROFILE.toLowerCase(), "");
        Assert.assertEquals(devUser.getEncoreText().toLowerCase(), LinkText.ENCORE.toLowerCase(), "");
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(), LinkText.APP_STORE.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 9, "");
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
        Assert.assertEquals(devUser.getLibraryText().toLowerCase(), LinkText.LIBRARY.toLowerCase(), "");
        Assert.assertEquals(devUser.getDashboardText().toLowerCase(), LinkText.DASHBOARD.toLowerCase(), "");
        Assert.assertEquals(devUser.getMyAppsText().toLowerCase(), LinkText.MY_APPS.toLowerCase(), "");
        Assert.assertEquals(devUser.getTransactionHistoryText().toLowerCase(), LinkText.TRANSACTION_HISTORY.toLowerCase(), "");
        Assert.assertEquals(devUser.getRevenueReportText().toLowerCase(), LinkText.REVENUE_REPORT.toLowerCase(), "");
        Assert.assertEquals(devUser.getSupportTicketText().toLowerCase(), LinkText.SUPPORT_TICKETS.toLowerCase(), "");
        Assert.assertEquals(devUser.getPublicProfileText().toLowerCase(), LinkText.PUBLIC_PROFILE.toLowerCase(), "");
        Assert.assertEquals(devUser.getEncoreText().toLowerCase(), LinkText.ENCORE.toLowerCase(), "");
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(), LinkText.APP_STORE.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 9, "");
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
        Assert.assertEquals(devUser.getPassportText().toLowerCase(), LinkText.PASSPORT.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 1, "");
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
        Assert.assertEquals(devUser.getPassportText().toLowerCase(), LinkText.PASSPORT.toLowerCase(), "");
        Assert.assertEquals(devUser.getLibraryText().toLowerCase(), LinkText.LIBRARY.toLowerCase(), "");
        Assert.assertEquals(devUser.getDashboardText().toLowerCase(), LinkText.DASHBOARD.toLowerCase(), "");
        Assert.assertEquals(devUser.getMyAppsText().toLowerCase(), LinkText.MY_APPS.toLowerCase(), "");
        Assert.assertEquals(devUser.getTransactionHistoryText().toLowerCase(), LinkText.TRANSACTION_HISTORY.toLowerCase(), "");
        Assert.assertEquals(devUser.getRevenueReportText().toLowerCase(), LinkText.REVENUE_REPORT.toLowerCase(), "");
        Assert.assertEquals(devUser.getSupportTicketText().toLowerCase(), LinkText.SUPPORT_TICKETS.toLowerCase(), "");
        Assert.assertEquals(devUser.getPublicProfileText().toLowerCase(), LinkText.PUBLIC_PROFILE.toLowerCase(), "");
        Assert.assertEquals(devUser.getEncoreText().toLowerCase(), LinkText.ENCORE.toLowerCase(), "");
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(), LinkText.APP_STORE.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 10, "");
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
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(), LinkText.APP_STORE.toLowerCase(), "");
        Assert.assertEquals(devUser.getLibraryText().toLowerCase(), LinkText.LIBRARY.toLowerCase(), "");
        Assert.assertEquals(devUser.getCampaignText().toLowerCase(), LinkText.CAMPAIGNS.toLowerCase(), "");
        Assert.assertEquals(devUser.getSchdulingText().toLowerCase(), LinkText.SCHEDULING.toLowerCase(), "");
        Assert.assertEquals(devUser.getKeyManagementText().toLowerCase(), LinkText.KEY_MANAGEMENT.toLowerCase(), "");
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(), LinkText.APP_STORE.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 6, "");
    }

    @Test
    public void _008_verify_admin_user_with_role_8_has_only_its_permissions() {
        if (user == null) {
            user = new User(driver);
        }
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_8, null);
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        Assert.assertEquals(adminPage.getSupportTicketsText().toLowerCase(), LinkText.SUPPORT_TICKETS.toLowerCase(), "");
        Assert.assertEquals(adminPage.getRetailersText().toLowerCase(), LinkText.RETAILERS.toLowerCase(), "");
        Assert.assertEquals(adminPage.getStatisticsText().toLowerCase(), LinkText.STATISTICS.toLowerCase(), "");
        Assert.assertEquals(adminPage.getUsersText().toLowerCase(), LinkText.USERS.toLowerCase(), "");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(), 4, "");
    }
}
