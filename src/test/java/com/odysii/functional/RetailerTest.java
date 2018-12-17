package com.odysii.functional;

import com.odysii.TestBase;
import com.odysii.selenium.page.openApps.retailer.*;
import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.ScreenSize;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import com.odysii.selenium.page.util.RequestHelper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetailerTest extends TestBase {
    private final static String APP_CLASS_NAME = "card";
    RetailerHomePage retailerHomePage;
    CampaignDesigner campaignDesigner;
    final String CATEGORYTEST = "Retailer Tests";
    @BeforeClass
    public void prepare(){
       prepareTest();

    }
    @Test//(priority = 1)
    public void _001_add_and_remove_app_library(){
        logger = extent.startTest("_001_add_and_remove_app_library").assignCategory();
        retailerHomePage.getAppLibrary();
        int expectedApp = driver.findElements(By.className(APP_CLASS_NAME)).size() + 1;
        AppStore appStore = retailerHomePage.getAppStore();
        wait(WAIT);
        expectedApp = expectedApp - appStore.addAppToLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        AppLibrary appLibrary = retailerHomePage.getAppLibrary();
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"Failed to adding application to library!");
        appLibrary.removeAppFromLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        Assert.assertEquals(actualApps - 1,driver.findElements(By.className(APP_CLASS_NAME)).size());
    }
   @Test//(priority = 2)
    public void _002_search_apps(){
       logger = extent.startTest("_002_search_apps").assignCategory(CATEGORYTEST);
        retailerHomePage.getAppStore();
        int expectedApp = 2;
        retailerHomePage.searchApps("auto");
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"App store search functionality failed!");

    }
    @Test//(priority = 3)
    public void _003_create_and_delete_campaign(){
        logger = extent.startTest("_003_create_and_delete_campaign").assignCategory(CATEGORYTEST);
        Campaign campaign = retailerHomePage.getCampaigs();
        wait(WAIT);
        int expectedCampaigns = campaign.getNumOfCampaigns() + 1;
        campaign.createCampaign("Auto Campaign","auto description");
        wait(WAIT);
        int actualCampaigns = campaign.getNumOfCampaigns();
        Assert.assertEquals(actualCampaigns,expectedCampaigns,"Adding a new campaign failed!");
        campaign.deleteCampaign();
        wait(WAIT);
        Assert.assertEquals(actualCampaigns - 1,campaign.getNumOfCampaigns());

    }
    @Test//(priority = 4)
    public void _004_set_up_campaign_screen_size_15_6_layout1_default_state(){
        logger = extent.startTest("_004_set_up_campaign_screen_size_15_6_layout1_default_state").assignCategory(CATEGORYTEST);
        Campaign campaign = retailerHomePage.getCampaigs();
        campaignDesigner = campaign.getDesignerPage();
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }
    @Test//(priority = 5)
    public void _005_set_up_campaign_screen_size_15_6_layout2_default_state(){
        logger = extent.startTest("_005_set_up_campaign_screen_size_15_6_layout2_default_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 6)
    public void _006_set_up_campaign_screen_size_15_6_layout3_default_state(){
        logger = extent.startTest("_006_set_up_campaign_screen_size_15_6_layout3_default_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 7)
    public void _007_set_up_campaign_screen_size_15_6_layout4_default_state(){
        logger = extent.startTest("_007_set_up_campaign_screen_size_15_6_layout4_default_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 8)
    public void _008_set_up_campaign_screen_size_15_6_layout1_idle_state(){
        logger = extent.startTest("_008_set_up_campaign_screen_size_15_6_layout1_idle_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 9)
    public void _009_set_up_campaign_screen_size_15_6_layout2_idle_state(){
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 10)
    public void _010_set_up_campaign_screen_size_15_6_layout3_idle_state(){
        logger = extent.startTest("_010_set_up_campaign_screen_size_15_6_layout3_idle_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 11)
    public void _011_set_up_campaign_screen_size_15_6_layout4_idle_state(){
        logger = extent.startTest("_011_set_up_campaign_screen_size_15_6_layout4_idle_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 12)
    public void _012_set_up_campaign_screen_size_15_6_layout1_fueling_state(){
        logger = extent.startTest("_012_set_up_campaign_screen_size_15_6_layout1_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 13)
    public void _013_set_up_campaign_screen_size_15_6_layout2_fueling_state(){
        logger = extent.startTest("_013_set_up_campaign_screen_size_15_6_layout2_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 14)
    public void _014_set_up_campaign_screen_size_15_6_layout3_fueling_state(){
        logger = extent.startTest("_014_set_up_campaign_screen_size_15_6_layout3_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 15)
    public void _011_set_up_campaign_screen_size_15_6_layout4_fueling_state(){
        logger = extent.startTest("_011_set_up_campaign_screen_size_15_6_layout4_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 16)
    public void _016_set_up_campaign_screen_size_15_6_layout1_payment_state(){
        logger = extent.startTest("_016_set_up_campaign_screen_size_15_6_layout1_payment_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 17)
    public void _017_set_up_campaign_screen_size_15_6_layout2_payment_state(){
        logger = extent.startTest("_017_set_up_campaign_screen_size_15_6_layout2_payment_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 18)
    public void _018_set_up_campaign_screen_size_15_6_layout3_payment_state(){
        logger = extent.startTest("_018_set_up_campaign_screen_size_15_6_layout3_payment_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 19)
    public void _019_set_up_campaign_screen_size_15_6_layout4_payment_state(){
        logger = extent.startTest("_019_set_up_campaign_screen_size_15_6_layout4_payment_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 20)
    public void _020_set_up_campaign_screen_size_15_6_layout1_post_fueling_state(){
        logger = extent.startTest("_020_set_up_campaign_screen_size_15_6_layout1_post_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 21)
    public void _021_set_up_campaign_screen_size_15_6_layout2_post_fueling_state(){
        logger = extent.startTest("_021_set_up_campaign_screen_size_15_6_layout2_post_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 22)
    public void _022_set_up_campaign_screen_size_15_6_layout3_post_fueling_state(){
        logger = extent.startTest("_022_set_up_campaign_screen_size_15_6_layout3_post_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 23)
    public void _023_set_up_campaign_screen_size_15_6_layout4_post_fueling_state(){
        logger = extent.startTest("_023_set_up_campaign_screen_size_15_6_layout4_post_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 24)
    public void _024_set_up_campaign_screen_size_10_4_layout1_default_state() {
        logger = extent.startTest("_024_set_up_campaign_screen_size_10_4_layout1_default_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 25)
    public void _025_set_up_campaign_screen_size_10_4_layout1_payment_state() {
        logger = extent.startTest("_025_set_up_campaign_screen_size_10_4_layout1_payment_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.PAYMENT, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 26)
    public void _026_set_up_campaign_screen_size_10_4_layout1_fueling_state() {
        logger = extent.startTest("_026_set_up_campaign_screen_size_10_4_layout1_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 27)
    public void _027_set_up_campaign_screen_size_10_4_layout1_idle_state() {
        logger = extent.startTest("_027_set_up_campaign_screen_size_10_4_layout1_idle_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.IDLE, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 28)
    public void _028_set_up_campaign_screen_size_10_4_layout1_post_fueling_state() {
        logger = extent.startTest("_028_set_up_campaign_screen_size_10_4_layout1_post_fueling_state").assignCategory(CATEGORYTEST);
        campaignDesigner.setUpCampaign(StateType.POST_FUELING, LayoutType.LAYOUT_1, ScreenSize.SIZE_10_4);
        Assert.assertTrue(campaignDesigner.isSaveSucceeded());
    }

    @Test//(priority = 29)
    public void _029_valid_application_packing() {
        logger = extent.startTest("_029_valid_application_packing").assignCategory(CATEGORYTEST);
        String url = "http://openappsqa.tveez.local:8080/openAppStore/webapi/application/4346/version/4349/pack";
        RequestHelper requestHelper = new RequestHelper();
        Assert.assertTrue(requestHelper.getRequest(url),"Failed to pack an application!");
    }

    @Test
    public void _004_edit_name_and_description_campaign(){
        Campaign campaign = retailerHomePage.getCampaigs();
        campaign.editCampaign("name test","description test");
        String expected = "Succeeded saving campaign";
        String actualTxt = driver.findElement(By.id("editCampaignSuccessErrorMessage")).getText();
        Assert.assertEquals(expected,actualTxt);

    }

}
