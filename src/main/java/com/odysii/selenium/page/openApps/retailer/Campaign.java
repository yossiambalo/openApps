package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Campaign extends PageObject {

    @FindBy(className = "cx-item-block")
    private List<WebElement> campaigns;
    @FindBy(id = "newCampaignModalToggleButton")
    private WebElement addNewCampaignBtn;
//    @FindBy(id = "campaignName")
//    private WebElement campaignName;
    private final String campaignNameID = "camapginName";
//    @FindBy(xpath = "//input[contains(@id, 'campaignDescription')]")
//    private WebElement campaignDescription;
    private final String campaignDescriptionID = "campaignDescription";
    @FindBy(id = "saveButton")
    private WebElement saveButton;
    @FindBy(id = "finishButton")
    private WebElement finishButton;
    @FindBy(className = "block-item-menu-icon")
    private List<WebElement> campaignListMenuBtn;
    @FindBy(xpath = "//button[contains(@id, 'deleteCampaign')]")
    private List<WebElement> deletBtn;
    @FindBy(id = "editCampaign")
    private WebElement editBtn;//navItem0
    @FindBy(id = "navItem25")
    private WebElement detialsLink;
    @FindBy(xpath = "//button[contains(text(), 'Confirm')]")
    private WebElement confirmBtn;
    @FindBy(id = "navItem1")
    private WebElement designLink;//accordion11
    @FindBy(id = "accordion11")
    private WebElement stateLbl;

    public Campaign(WebDriver driver) {
        super(driver);
    }
    public int getNumOfCampaigns(){
        return campaigns.size();
    }
    public void createCampaign(String campaignName, String description){
        isElementPresent(addNewCampaignBtn);
        addNewCampaignBtn.click();
        isElementPresent(webDriver.findElement(By.id(campaignNameID)));
        webDriver.findElement(By.id(campaignNameID)).sendKeys(campaignName);
        webDriver.findElement(By.id(campaignDescriptionID)).sendKeys(description);
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
        if (!stateLbl.isDisplayed()) {
            designLink.click();
        }
        return new CampaignDesigner(webDriver);
    }
    public void editCampaign(String campaignName, String description){
        int counter = 0;
        isElementPresent(campaignListMenuBtn.get(0));
        campaignListMenuBtn.get(0).click();
        editBtn.click();
        isElementPresent(detialsLink);
        detialsLink.click();
        while (!isElementPresent(By.id("campaignName")) && counter < 5){
            wait(WAIT);
            counter ++;
        }
        webDriver.findElement(By.id("campaignName")).sendKeys(campaignName);
        webDriver.findElement(By.id(campaignDescriptionID)).sendKeys(description);
        saveButton.click();
    }
}
