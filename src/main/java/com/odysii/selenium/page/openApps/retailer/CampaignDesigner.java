package com.odysii.selenium.page.openApps.retailer;


import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CampaignDesigner extends PageObject {

    @FindBy(xpath = "//a[contains(text(), 'Default')]")
    private WebElement defaultLink;
    @FindBy(className = "ng-touched")
    private WebElement screenSizeDdl;
    @FindBy(id = "campaignLayoutsModalButton")
    private WebElement layoutBtn;
    @FindBy(className = "box-shadow")
    private List<WebElement> layoutBox;
    @FindBy(className = "position-absolute")
    private WebElement layoutTyp1;
    @FindBy(xpath = "//h5[contains(text(), 'Automation App:')]")
    private WebElement applicationForLayout;

    public CampaignDesigner(WebDriver driver) {
        super(driver);
    }

    public void setUpCampaign(StateType stateType, LayoutType layoutType, String screenSize){
        screenSizeDdl.sendKeys(screenSize);
        switch (stateType){
            case DEFAULT:
                defaultLink.click();
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        layoutBox.get(0).click();
                        (new Actions(webDriver)).dragAndDrop(applicationForLayout, layoutTyp1).perform();
                }
        }
    }
}
