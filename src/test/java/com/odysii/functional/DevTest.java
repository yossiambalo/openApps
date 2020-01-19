package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.SupportTicket;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.openApps.dev.summary.Summary;
import com.odysii.selenium.page.openApps.helper.appDetails.AvailabilityType;
import com.odysii.selenium.page.openApps.retailer.AppStore;
import com.odysii.selenium.page.openApps.retailer.Campaign;
import com.odysii.selenium.page.openApps.retailer.CampaignDesigner;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.ScreenSize;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;


public class DevTest extends TestBase {
    private static final String DEPEND_APP_DESCRIPTION = "It is dependent application";
    private static final String CORE_APP_DESCRIPTION = "It is core application";
    @BeforeClass
    public void login() {
        deleteAllApps();
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(RETAILER_USER_NAME);
        editUser.edit(RoleType.ROLE_7,null);
        editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_1,null);
        isRoleConfig = true;
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME, RETAILER_USER_PASS, UserType.RETAILER);
        category = "Dev";
    }

    @Test//(priority = 1)
    public void _001_valid_add_new_app() {
        //get number of live apps from retailer page
        retailerHomePage.getAppStore();
        appListBeforeAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        List<WebElement> appsList = driver.findElements(By.className(APP_CLASS_NAME));
        int appsSize = appsList.size();
        int expectedValue = appsSize + 1;
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        Dependencies dependencies = appDetails.setUpAppDetailsFromPropFile("app_details.properties");
        //dependencies.checkApplication();
        //dependencies.selectVersion();
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue, actualValue, "Failed to create a new code!");
        Assert.assertTrue(myApps.getTitle(actualValue - 1).toLowerCase().contains(appDetails.getAppTitle().toLowerCase()));
        Assert.assertTrue(myApps.getDescription(actualValue - 1).toLowerCase().contains(appDetails.getAppDescription().toLowerCase()));
    }

    //@Test(priority = 2, enabled = false)//Not supported anymore
    public void _002_valid_app_reject_no_fee() {
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        setApplicationID();
        showUp.certify();
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        showUp.backToMyApps();
        SupportTicket devSupportTicket = devUser.getSupportTicket();
        Assert.assertEquals(devSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.OPEN.getStatus().toLowerCase(),"Status should be Open but found "+devSupportTicket.getAppStatus()+" in dev page!");
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        SupportTicket adminSupportTicket = adminPage.getSupportTicketsLink();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.OPEN.getStatus().toLowerCase(),"Status should be Open but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        adminSupportTicket.rejectNoFee();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.REJECTED_NO_FEE.getStatus().toLowerCase(),"Status should be Rejected no fee but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        //Valid rejected
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        int counter = 0;
        do {
            wait(2000);
            counter++;
        } while (driver.findElements(By.className(APP_CLASS_NAME)).size() < actualAppList.size() && counter < 5);
        showUp = myApps.showUp(actualAppList.size() - 1);
        Assert.assertEquals(showUp.getStatus().trim(), ApplicationStatus.REJECT.getStatus());
        showUp.backToMyApps();
        devSupportTicket = devUser.getSupportTicket();
        Assert.assertEquals(devSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.REJECTED_NO_FEE.getStatus().toLowerCase(),"Status should be Rejected no fee but found "+devSupportTicket.getAppStatus()+" in dev page!");
        //showUp.backToMyApps();
    }

    //@Test(priority = 3,enabled = false)
    public void _003_valid_reject_with_fee() {
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        //get the created app
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        SupportTicket adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.rejectWithFee();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.REJECT.getStatus().toLowerCase(),"Status should be Rejected no fee but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        //Valid rejected
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        int counter = 0;
        do {
            wait(2000);
            counter++;
        } while (driver.findElements(By.className(APP_CLASS_NAME)).size() < actualAppList.size() && counter < 5);
        showUp = myApps.showUp(actualAppList.size() - 1);
        String status = null;
        counter = 0;
        do {
            status = showUp.getStatus().trim();
            wait(2000);
            counter++;
        } while (!status.equals(ApplicationStatus.REJECT.getStatus()) && counter < 5);
        Assert.assertEquals(status, ApplicationStatus.REJECT.getStatus());
        showUp.backToMyApps();
        SupportTicket devSupportTicket = devUser.getSupportTicket();
        Assert.assertEquals(devSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.REJECT.getStatus().toLowerCase(),"Status should be Rejected but found "+devSupportTicket.getAppStatus()+" in dev page!");
    }

    @Test//(priority = 4)
    public void _004_edit_and_certify_and_go_live() {
        myApps = devUser.getMyAppsPage(driver);
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        Summary summary = new Summary(driver);
        showUp.editApp(summary);
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        SupportTicket adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.approve();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        //Valid certified
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        int counter = 0;
        do {
            wait(2000);
            counter++;
        } while (driver.findElements(By.className(APP_CLASS_NAME)).size() < actualAppList.size() && counter < 5);
        showUp = myApps.showUp(actualAppList.size() - 1);
        Assert.assertEquals(showUp.getStatus().trim(), ApplicationStatus.CERTIFIED.getStatus());
        showUp.addApplicationToStore();
        Assert.assertEquals(showUp.getStatus().trim(), ApplicationStatus.LIVE.getStatus());
        showUp.backToMyApps();
        SupportTicket devSupportTicket = devUser.getSupportTicket();
        Assert.assertEquals(devSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+devSupportTicket.getAppStatus()+" in dev page!");
        user.logout();
    }
    @Test//(priority = 5)
    public void _005_valid_app_add_to_app_store(){
        try {
            //Valid app added to retailer store
            retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
            retailerHomePage.getAppStore();
            int appListAfterAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
            Assert.assertEquals(appListAfterAdding,appListBeforeAdding + 1);
        }catch (Exception e){
            e.getMessage();
        }finally {
            user.logout();
        }
    }
    @Test//(priority = 6)
    public void _006_valid_add_new_version_to_application(){
        DevHomePage devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        ShowUp showUp = myApps.showUp(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        showUp.getAppVersion();
        AppDetails appDetails = new AppDetails(driver);
        Dependencies dependencies = appDetails.setUpAppDetails("1.0.8");
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(5000);
        Assert.assertEquals(showUp.getStatus(1).trim(),ApplicationStatus.PRESUBMITTED.getStatus());
    }
    @Test
    public void _007_valid_create_no_auto_add_dependent_application_designer_drag_and_drop(){
        /**
         * =============Start Create core application===========
         */
        Random random = new Random();
        String coreAppName = random.nextInt(10000)+"_CoreAppNoAuto";
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        Dependencies dependencies = appDetails.setUpAppDetails(coreAppName,"1.0.2."+random.nextInt(8000),CORE_APP_DESCRIPTION,PriceType.PER_SITE_PER_YEAR,"4", AvailabilityType.PUBLIC,false,false);
        Assert.assertNotNull(dependencies,"Failed to set up app details!");
        //dependencies.checkApplication();
        //String coreAppTitle = dependencies.getDependencyAppName();
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        SupportTicket adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.approve();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.addApplicationToStore();
        /**
         * =============End Create core application===========
         */
        /**
         * =============Start Create dependent application===========
         */
        String dependentAppName = random.nextInt(10000)+"_DependentNoAuto";
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        dependencies = appDetails.setUpAppDetails(dependentAppName,"1.0.2."+random.nextInt(8000),DEPEND_APP_DESCRIPTION,PriceType.PER_SITE_PER_YEAR,"4", AvailabilityType.PUBLIC,false,false);
        Assert.assertNotNull(dependencies,"Failed to set up app details!");
        dependencies.checkApplication(coreAppName);
        //String coreAppTitle = dependencies.getDependencyAppName();
        uploadCode = dependencies.clickOnNextButton();
        marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.approve();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.addApplicationToStore();
        /**
         * =============End Create dependent application===========
         */
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        AppStore appStore = retailerHomePage.getAppStore();
        appStore.addAppToLibrary(coreAppName);
        appStore.addAppToLibrary(dependentAppName);
        Campaign campaign = retailerHomePage.getCampaign();
        CampaignDesigner campaignDesigner = campaign.getDesignerPage();
        campaignDesigner.setAppNameForDragAndDrop(coreAppName);
        campaignDesigner.setDeleteApps(true);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1,1, ScreenSize.SIZE_15_6,true);
        campaignDesigner.setDeleteApps(false);
        campaignDesigner.setAppNameForDragAndDrop(dependentAppName);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1,1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded(2));

    }
    @Test
    public void _008_valid_create_auto_add_dependent_application_designer_drag_and_drop(){
        /**
         * =============Start Create core application===========
         */
        Random random = new Random();
        String coreAppName = random.nextInt(10000)+"_CoreAppAouto";
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        MyApps myApps = devUser.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        Dependencies dependencies = appDetails.setUpAppDetails(coreAppName,"1.0.2."+random.nextInt(8000),CORE_APP_DESCRIPTION,PriceType.PER_SITE_PER_YEAR,"4", AvailabilityType.PUBLIC,true,false);
        Assert.assertNotNull(dependencies,"Failed to set up app details!");
        //dependencies.checkApplication();
        //String coreAppTitle = dependencies.getDependencyAppName();
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        AdminPage adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        SupportTicket adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.approve();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.addApplicationToStore();
        /**
         * =============End Create core application===========
         */
        /**
         * =============Start Create dependent application===========
         */
        String dependentAppName = random.nextInt(10000)+"_DependentAuto";
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        appDetails = myApps.clickAddNewAppBtn();
        dependencies = appDetails.setUpAppDetails(dependentAppName,"1.0.2."+random.nextInt(8000),DEPEND_APP_DESCRIPTION,PriceType.PER_SITE_PER_YEAR,"4", AvailabilityType.PUBLIC,false,false);
        Assert.assertNotNull(dependencies,"Failed to set up app details!");
        dependencies.checkApplication(coreAppName);
        //String coreAppTitle = dependencies.getDependencyAppName();
        uploadCode = dependencies.clickOnNextButton();
        marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.approve();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.addApplicationToStore();
        /**
         * =============End Create dependentAuto application===========
         */
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        AppStore appStore = retailerHomePage.getAppStore();
        appStore.addAppToLibrary(dependentAppName);
        Campaign campaign = retailerHomePage.getCampaign();
        CampaignDesigner campaignDesigner = campaign.getDesignerPage();
        campaignDesigner.setAppNameForDragAndDrop(dependentAppName);
        campaignDesigner.setDeleteApps(true);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1,1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded(2));

    }
    @Test
    public void _009_valid_hide_app_in_appStore(){
        Random random = new Random();
        /**
         * =============Start Create hidden application===========
         */
        String dependentAppName = random.nextInt(10000)+"_HiddenApp";
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        Dependencies dependencies = appDetails.setUpAppDetails(dependentAppName,"1.0.2."+random.nextInt(8000),DEPEND_APP_DESCRIPTION,PriceType.PER_SITE_PER_YEAR,"4", AvailabilityType.PUBLIC,false,true);
        Assert.assertNotNull(dependencies,"Failed to set up app details!");
        //dependencies.checkApplication(coreAppName);
        //String coreAppTitle = dependencies.getDependencyAppName();
        UploadCode uploadCode = dependencies.clickOnNextButton();
        Marketing marketing = uploadCode.upload(zipFile,true);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        ShowUp showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.certify();
        wait(WAIT);
        Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(), showUp.getStatus().trim());
        user.logout();
        //Admin approve
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
        SupportTicket adminSupportTicket = adminPage.getSupportTicketsLink();
        adminSupportTicket.approve();
        adminSupportTicket.backToSupportTicket();
        Assert.assertEquals(adminSupportTicket.getAppStatus().toLowerCase(),ApplicationStatus.APPROVED.getStatus().toLowerCase(),"Status should be Approved but found "+adminSupportTicket.getAppStatus()+" in admin page!");
        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME, DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        showUp = myApps.showUp(actualAppList.get(actualValue - 1));
        showUp.addApplicationToStore();
        /**
         * =============End Create hidden application===========
         */
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
        AppStore appStore = retailerHomePage.getAppStore();
        Assert.assertFalse(appStore.isAppExist(dependentAppName));
    }
//    @AfterClass
//    public void clean(){
//        if (!user.isUserLoggedIn(ADMIN_USER_NAME)) {
//            adminPage = (AdminPage) user.login(ADMIN_USER_NAME, ADMIN_USER_PASS, UserType.ADMIN);
//        }
//        setAdminCookie();
//        RequestHelper requestHelper = null;
//        if (applicationIDToDelete != null){
//            requestHelper = new RequestHelper();
//            for (String appID: applicationIDToDelete){
//                if (!requestHelper.deleteRequest(openAppsUrl+"/webapi/application/"+appID,token)){
//                    try {
//                        throw new Exception("Failed to delete application number: "+appID);
//                    } catch (Exception e) {
//                        System.out.println("Failed to delete application number: "+appID);
//                    }
//                }
//            }
//        }
//        driver.quit();
//    }
}
