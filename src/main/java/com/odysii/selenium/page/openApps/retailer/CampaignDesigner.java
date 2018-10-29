package com.odysii.selenium.page.openApps.retailer;


import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CampaignDesigner extends PageObject {

    @FindBy(xpath = "//a[contains(text(), 'Default')]")
    private WebElement defaultLink;
    @FindBy(className = "ng-valid")
    private WebElement screenSizeDdl;
    @FindBy(id = "campaignLayoutsModalButton")
    private WebElement layoutBtn;
    @FindBy(className = "frame1")
    private List<WebElement> layoutBox1;
    @FindBy(id = "saveButton")
    private WebElement saveBtn;
    @FindBy(className = "position-absolute")
    private WebElement layoutTyp1;
    @FindBy(xpath = "//h5[contains(text(), 'Automation App:')]")
    private WebElement applicationForLayout;
    @FindBy(css = ".col-8 .box-shadow .frame")
    private List<WebElement> appContainer;

    public CampaignDesigner(WebDriver driver) {
        super(driver);
    }

    public void setUpCampaign(StateType stateType, LayoutType layoutType, String screenSize){
        isElementPresent(screenSizeDdl);
        screenSizeDdl.sendKeys(screenSize);
        switch (stateType){
            case DEFAULT:
                defaultLink.click();
                isElementPresent(layoutBtn);
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        setUpContainer(1);
                        break;
                    case LAYOUT_2:
                        setUpContainer(2);
                        break;
                    case LAYOUT_3:
                        setUpContainer(3);
                        break;
                    case LAYOUT_4:
                        setUpContainer(4);
                        break;
                        default:
                            ////
                }
        }
        isElementPresent(saveBtn);
        saveBtn.click();
    }
//    public boolean isSaveSucceeded(){
//
//    }
    public boolean getNumOfDraggedApps(int expectedApps){
        boolean res = true;
        int i;
        for(i = 0; i < expectedApps; i++){
            if(!appContainer.get(i).getCssValue("background-image").contains("openAppStore/webapi/storage")){
                res = false;
            }
        }
        return res;
    }
    private void setUpContainer(int numOfApps){
        isElementPresent(layoutBox1.get(numOfApps));
        layoutBox1.get(numOfApps).click();
        for (int i = 0; i < numOfApps; i++){
            SeleniumUtils.dragAndDrop(webDriver,applicationForLayout, appContainer.get(i));
        }
    }
}
