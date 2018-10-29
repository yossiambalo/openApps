package com.odysii;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.retailer.*;
import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.ScreenSize;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class RetailerTest extends TestBase {
    private final static String APP_CLASS_NAME = "card";
    private final static String RETAILER_USER_NAME = "retailer";
    private final static String RETAILER_USER_PASS = "123456";
    RetailerHomePage retailerHomePage;
    CampaignDesigner campaignDesigner;
    User user;
    @BeforeClass
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS, UserType.RETAILER);

    }
    @Test(priority = 1)
    public void _001_add_and_remove_app_library(){
        retailerHomePage.getAppLibrary();
        int expectedApp = driver.findElements(By.className(APP_CLASS_NAME)).size() + 1;
        AppStore appStore = retailerHomePage.getAppStore();
        wait(WAIT);
        appStore.addAppToLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        AppLibrary appLibrary = retailerHomePage.getAppLibrary();
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"Failed to adding application to library!");
        appLibrary.removeAppFromLibrary(driver.findElements(By.className(APP_CLASS_NAME)).size() - 1);
        wait(WAIT);
        Assert.assertEquals(actualApps - 1,driver.findElements(By.className(APP_CLASS_NAME)).size());
    }
   @Test(priority = 2)
    public void _002_search_apps(){
        retailerHomePage.getAppStore();
        int expectedApp = 3;
        retailerHomePage.searchApps("Spin");
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"App store search functionality failed!");

    }
    @Test(priority = 3)
    public void _003_create_and_delete_campaign(){
        Campaign campaign = retailerHomePage.getCampaigs();
        int expectedCampaigns = campaign.getNumOfCampaigns() + 1;
        campaign.createCampaign("Auto Campaign","auto description");
        wait(WAIT);
        int actualCampaigns = campaign.getNumOfCampaigns();
        Assert.assertEquals(actualCampaigns,expectedCampaigns,"Adding a new campaign failed!");
        campaign.deleteCampaign();
        wait(WAIT);
        Assert.assertEquals(actualCampaigns - 1,campaign.getNumOfCampaigns());

    }
    @Test(priority = 4)
    public void _004_set_up_campaign_layout1_default_state(){
        Campaign campaign = retailerHomePage.getCampaigs();
        campaignDesigner = campaign.getDesignerPage();
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.getNumOfDraggedApps(1));
    }
    @Test(priority = 5)
    public void _005_set_up_campaign_layout2_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_2, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.getNumOfDraggedApps(2));
    }
    @Test(priority = 6)
    public void _006_set_up_campaign_layout3_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_3, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.getNumOfDraggedApps(3));
    }
    @Test(priority = 7)
    public void _007_set_up_campaign_layout4_default_state(){
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_4, ScreenSize.SIZE_15_6);
        Assert.assertTrue(campaignDesigner.getNumOfDraggedApps(3));
    }
    //`@Test
    public void _004_edit_name_and_description_campaign(){
        Campaign campaign = retailerHomePage.getCampaigs();
        //wait(4000);
//        campaign.editCampaign("name test","description test");
//        String expected = "editCampaignSuccessErrorMessage";
//        String actualTxt = driver.findElement(By.id("editCampaignSuccessErrorMessage")).getText();
//        Assert.assertEquals(expected,actualTxt);

    }

}
