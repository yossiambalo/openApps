package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Campaign extends PageObject {

    @FindBy(className = "cx-item-block")
    private List<WebElement> campaigns;
    @FindBy(id = "newCampaignModalToggleButton")
    private WebElement addNewCampaignBtn;
    @FindBy(id = "camapginName")
    private WebElement camapginName;
    @FindBy(id = "campaignDescription")
    private WebElement campaignDescription;
    @FindBy(id = "finishButton")
    private WebElement finishButton;
    @FindBy(className = "block-item-menu-icon")
    private List<WebElement> campaignListMenuBtn;

    public Campaign(WebDriver driver) {
        super(driver);
    }
    public int getNumOfCampaigns(){
        return campaigns.size();
    }
    public void createCampaign(String campaignName, String description){
        addNewCampaignBtn.click();
        camapginName.sendKeys(campaignName,description);
        campaignDescription.sendKeys(description);
        finishButton.click();
    }
}
