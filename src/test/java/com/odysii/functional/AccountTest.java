package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.helper.LinkText;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountTest extends TestBase {

    private User user = null;
    @Test
    public void _001_verify_dev_user_with_role_1_has_only_its_permissions(){
       if (user == null){
           user = new User(driver);
       }
        DevHomePage devUser = (DevHomePage)user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        Assert.assertEquals(devUser.getDashboardText().toLowerCase(),LinkText.DASHBOARD.toLowerCase(),"");
        Assert.assertEquals(devUser.getMyAppsText().toLowerCase(),LinkText.MY_APPS.toLowerCase(),"");
        Assert.assertEquals(devUser.getTransactionHistoryText().toLowerCase(),LinkText.TRANSACTION_HISTORY.toLowerCase(),"");
        Assert.assertEquals(devUser.getRevenueReportText().toLowerCase(),LinkText.REVENUE_REPORT.toLowerCase(),"");
        Assert.assertEquals(devUser.getSupportTicketText().toLowerCase(),LinkText.SUPPORT_TICKETS.toLowerCase(),"");
        Assert.assertEquals(devUser.getPublicProfileText().toLowerCase(),LinkText.PUBLIC_PROFILE.toLowerCase(),"");
        Assert.assertEquals(devUser.getEncoreText().toLowerCase(),LinkText.ENCORE.toLowerCase(),"");
        Assert.assertEquals(devUser.getAppStoreText().toLowerCase(),LinkText.APP_STORE.toLowerCase(),"");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(),8, "");
    }
    @Test
    public void _002_verify_admin_user_with_role_8_has_only_its_permissions(){
        if (user == null){
            user = new User(driver);
        }
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        Assert.assertEquals(adminPage.getSupportTicketsText().toLowerCase(),LinkText.SUPPORT_TICKETS.toLowerCase(),"");
        Assert.assertEquals(adminPage.getRetailersText().toLowerCase(),LinkText.RETAILERS.toLowerCase(),"");
        Assert.assertEquals(adminPage.getStatisticsText().toLowerCase(),LinkText.STATISTICS.toLowerCase(),"");
        Assert.assertEquals(adminPage.getUsersText().toLowerCase(),LinkText.USERS.toLowerCase(),"");
        Assert.assertEquals(driver.findElements(By.className("nav-text")).size(),4, "");
    }
}
