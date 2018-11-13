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
    @FindBy(xpath = "//input[contains(@id, 'camapginName')]")
    private WebElement campaignName;
    @FindBy(xpath = "//input[contains(@id, 'campaignDescription')]")
    private WebElement campaignDescription;
    @FindBy(id = "finishButton")
    private WebElement finishButton;
    @FindBy(className = "block-item-menu-icon")
    private List<WebElement> campaignListMenuBtn;
    @FindBy(xpath = "//button[contains(@id, 'deleteCampaign')]")
    private List<WebElement> deletBtn;
    @FindBy(id = "editCampaign")
    private WebElement editBtn;
    @FindBy(xpath = "//button[contains(text(), 'Confirm')]")
    private WebElement confirmBtn;
    @FindBy(xpath = "//span[contains(text(), 'Design')]")
    private WebElement designLink;

    public Campaign(WebDriver driver) {
        super(driver);
    }
    public int getNumOfCampaigns(){
        return campaigns.size();
    }
    public void createCampaign(String campaignName, String description){
        isElementPresent(addNewCampaignBtn);
        addNewCampaignBtn.click();
        isElementPresent(this.campaignName);
        this.campaignName.sendKeys(campaignName);
        campaignDescription.sendKeys(description);
        finishButton.click();
    }
    public void deleteCampaign(){
        campaignListMenuBtn.get(0).click();
        deletBtn.get(0).click();
        wait(WAIT);
        confirmBtn.click();
    }
    public CampaignDesigner getDesignerPage(){
        isElementPresent(campaignListMenuBtn.get(0));
        campaignListMenuBtn.get(0).click();
        editBtn.click();
        isElementPresent(designLink);
        designLink.click();
        return new CampaignDesigner(webDriver);
    }
    public void editCampaign(String campaignName, String description){
        isElementPresent(campaignListMenuBtn.get(0));
        campaignListMenuBtn.get(0).click();
        editBtn.click();
        isElementPresent(this.campaignName);
        this.campaignName.sendKeys(campaignName);
        campaignDescription.sendKeys(description);
        finishButton.click();
    }
}
