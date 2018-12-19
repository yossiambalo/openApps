package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Scheduling extends PageObject {
    @FindBy(id = "siteSelectionAccordion")
    private WebElement siteArea;
    private String siteAreaCheckBoxTagName = "i";
    @FindBy(className = "dropdown")
    private List<WebElement> campaignListMenuBtn;
    @FindBy(id = "editCampaignSchedule")
    private List<WebElement> editCampaignSchedule;
    public Scheduling(WebDriver driver) {
        super(driver);
    }
    public void deployToAll(AreaType areaType){
        isElementPresent(campaignListMenuBtn.get(0));
        campaignListMenuBtn.get(0).click();
        isElementPresent(editCampaignSchedule.get(0));
        editCampaignSchedule.get(0).click();
        switch (areaType){
            case NORTH_US:
                isElementPresent(siteArea);
                WebElement e =  siteArea.findElements(By.className(siteAreaCheckBoxTagName)).get(0);
                if (!e.getAttribute("class").contains("check")){
                   e.click();
            }
                break;
            case SOUTH_US:
                ///add logic here....
                break;
                default:;
                ///do nothing
        }
    }
    public void deployToDispenser(AreaType areaType) {
    }
}
