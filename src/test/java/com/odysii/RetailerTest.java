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
    User user;
    @BeforeClass
    public void login(){
        user = new User(driver);
        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS, UserType.RETAILER);
    }
    @Test
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
    @Test
    public void _002_search_apps(){
        retailerHomePage.getAppStore();
        int expectedApp = 3;
        retailerHomePage.searchApps("Spin");
        wait(WAIT);
        int actualApps = driver.findElements(By.className(APP_CLASS_NAME)).size();
        Assert.assertEquals(actualApps,expectedApp,"App store search functionality failed!");
    }
    @Test
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
    @Test
    public void _003_set_up_campaign_layout1(){
        Campaign campaign = retailerHomePage.getCampaigs();
        CampaignDesigner campaignDesigner = campaign.getDesignerPage();
        campaignDesigner.setUpCampaign(StateType.DEFAULT, LayoutType.LAYOUT_1, ScreenSize.SIZE_15_6);
    }

}
