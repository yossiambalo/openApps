package com.odysii.functional.Retailer;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.EditUser;
import com.odysii.selenium.page.openApps.admin.UsersPage;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.retailer.*;
import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.ScreenSize;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import com.odysii.selenium.page.util.RequestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class RetailerTest extends TestBase {
    private final static String APP_CLASS_NAME = "card";
    CampaignDesigner campaignDesigner;
    @BeforeClass
    public void prepare(){
        Assert.assertTrue(updateUser(7));
        category = "Retailer";
        user = new User(driver);
        adminPage = (AdminPage) user.login(ADMIN_USER_NAME,ADMIN_USER_PASS, UserType.ADMIN);
        UsersPage usersPage = adminPage.getUsersPage();
        EditUser editUser = usersPage.getUser(RETAILER_USER_NAME);
        editUser.edit(RoleType.ROLE_7,null);
        editUser = usersPage.getUser(DEV_USER_NAME);
        editUser.edit(RoleType.ROLE_1,null);
        isRoleConfig = true;
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS, UserType.RETAILER);
//        prepareTest("app_details.properties", ApplicationStatus.SUBMITTED);
//        prepareTest("app_details.properties", ApplicationStatus.LIVE);
    }
    //@Test//(priority = 1)
    public void _001_valid_deploy_to_dispenser(){
        Scheduling scheduling = retailerHomePage.getScheduling();
        Assert.assertTrue(scheduling.deployToAll(AreaType.NORTH_US));
    }
   // @Test//(priority = 1)
    public void _002_add_and_remove_app_library(){
        boolean flag = false;
        AppStore appStore = null;
        retailerHomePage.getAppLibrary();
        int expectedApp = driver.findElements(By.className(APP_CLASS_NAME)).size() + 1;
        if (expectedApp < 2){
            prepareTest("app_details.properties", ApplicationStatus.LIVE);
            prepareTest("app_details.properties",ApplicationStatus.LIVE);
            flag = true;
        }
        if (flag){
            retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS, UserType.RETAILER);
            appStore = retailerHomePage.getAppStore();
            wait(WAIT);
            appStore.addAppToLibrary(0);
            appStore.addAppToLibrary(1);
            retailerHomePage.getAppLibrary();
            expectedApp = driver.findElements(By.className(APP_CLASS_NAME)).size() + 1;
        }
        appStore = retailerHomePage.getAppStore();
        wait(WAIT);
        expectedApp = expectedApp - appStore.addAppToLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        AppLibrary appLibrary = retailerHomePage.getAppLibrary();
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"Failed to adding code to library!");
        appLibrary.removeAppFromLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        driver.navigate().refresh();
        wait(WAIT);
        Assert.assertEquals(driver.findElements(By.className(APP_CLASS_NAME)).size(), actualApps - 1);
    }
   // @Test//(priority = 2)
    public void _003_search_apps(){
        int expectedApp = 0;
        retailerHomePage.getAppStore();
        wait(WAIT);
        List<WebElement> apps = driver.findElements(By.xpath("//h5[contains(@class, 'cx-card-title')]"));
        for (WebElement e : apps){
            if(e.getText().toLowerCase().contains("Automation App:".toLowerCase())){
                expectedApp++;
            }
        }
        retailerHomePage.searchApps("automation");
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"App store search functionality failed!");

    }
   // @Test//(priority = 3,retryAnalyzer = Retry.class)
    public void _004_create_and_delete_campaign(){
        int counter = 0;
        Campaign campaign = retailerHomePage.getCampaign();
        wait(WAIT);
        int expectedCampaigns = campaign.getNumOfCampaigns() + 1;
        campaign.createCampaign("Auto Campaign","auto description");
        wait(WAIT);
        int actualCampaigns = campaign.getNumOfCampaigns();
        Assert.assertEquals(actualCampaigns,expectedCampaigns,"Adding a new campaign failed!");
        if (expectedCampaigns > 1){
            campaign.deleteCampaign();
            counter++;
        }
        wait(WAIT);
        Assert.assertEquals(actualCampaigns - counter,campaign.getNumOfCampaigns());

    }
    @Test//(priority = 4)
    public void _005_valid_set_up_campaign_screen_size_15_6_layout1_default_state(){
        Campaign campaign = retailerHomePage.getCampaign();
        campaignDesigner = campaign.getDesignerPage();
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }
   // @Test//(priority = 5)
    public void _006_valid_set_up_campaign_screen_size_15_6_layout2_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 6)
    public void _007_valid_set_up_campaign_screen_size_15_6_layout3_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 7)
    public void _008_valid_set_up_campaign_screen_size_15_6_layout4_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 8)
    public void _009_valid_set_up_campaign_screen_size_15_6_layout1_idle_state(){
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 9)
    public void _010_valid_set_up_campaign_screen_size_15_6_layout2_idle_state(){
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 10)
    public void _011_valid_set_up_campaign_screen_size_15_6_layout3_idle_state(){
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 11)
    public void _012_valid_set_up_campaign_screen_size_15_6_layout4_idle_state(){
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 12)
    public void _013_valid_set_up_campaign_screen_size_15_6_layout1_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 13)
    public void _014_valid_set_up_campaign_screen_size_15_6_layout2_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 14)
    public void _015_valid_set_up_campaign_screen_size_15_6_layout3_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 15)
    public void _016_valid_set_up_campaign_screen_size_15_6_layout4_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 16)
    public void _017_valid_set_up_campaign_screen_size_15_6_layout1_payment_state(){
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 17)
    public void _018_valid_set_up_campaign_screen_size_15_6_layout2_payment_state(){
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 18)
    public void _019_valid_set_up_campaign_screen_size_15_6_layout3_payment_state(){
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 19)
    public void _020_valid_set_up_campaign_screen_size_15_6_layout4_payment_state(){
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 20)
    public void _021_valid_set_up_campaign_screen_size_15_6_layout1_post_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 21)
    public void _022_valid_set_up_campaign_screen_size_15_6_layout2_post_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 22)
    public void _023_valid_set_up_campaign_screen_size_15_6_layout3_post_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 23)
    public void _024_valid_set_up_campaign_screen_size_15_6_layout4_post_fueling_state(){
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 24)
    public void _025_valid_set_up_campaign_screen_size_10_4_layout1_default_state() {
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 25)
    public void _026_valid_set_up_campaign_screen_size_10_4_layout1_payment_state() {
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 26)
    public void _027_valid_set_up_campaign_screen_size_10_4_layout1_fueling_state() {
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 27)
    public void _028_valid_set_up_campaign_screen_size_10_4_layout1_idle_state() {
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

   // @Test//(priority = 28)
    public void _029_valid_set_up_campaign_screen_size_10_4_layout1_post_fueling_state() {
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4,false);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }
    @Test
    public void _030_valid_add_backround_app_screen_size_15_6_layout1_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6,true);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test(enabled = false)
    public void _030_valid_application_signing() {
        String url = "http://odysiiopenappsqa.gilbarco.com:8080/openAppStore/webapi/code/4346/version/4349/pack";
        RequestHelper requestHelper = new RequestHelper();
        Assert.assertTrue(requestHelper.getRequest(url),"Failed to signing application!");
    }

    @Test
    public void _031_edit_name_and_description_campaign(){
        Campaign campaign = retailerHomePage.getCampaign();
        campaign.editCampaign("name test","description test");
        String expected = "Saved campaign successfully";
        String actualTxt = driver.findElement(By.id("editCampaignSuccessErrorMessage")).getText();
        Assert.assertEquals(actualTxt,expected);

    }

}
