package com.odysii.selenium.page.openApps.retailer;


import com.odysii.selenium.page.openApps.retailer.helper.LayoutType;
import com.odysii.selenium.page.openApps.retailer.helper.ScreenSize;
import com.odysii.selenium.page.openApps.retailer.helper.StateType;
import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CampaignDesigner extends PageObject {

    @FindBy(xpath = "//a[contains(text(), 'All States')]")
    private WebElement defaultLink;
    @FindBy(xpath = "//a[contains(text(), 'Idle')]")
    private WebElement idleLink;
    @FindBy(xpath = "//a[contains(text(), 'Fueling')]")
    private WebElement fuelingLink;
    @FindBy(xpath = "//a[contains(text(), 'Payment')]")
    private WebElement paymentLink;
    @FindBy(xpath = "//a[contains(text(), 'Post-fueling')]")
    private WebElement postFuelingtLink;
    @FindBy(id = "campaignLayoutScreenSize")
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
    @FindBy(xpath = "//h5[contains(text(), '997')]")
    private WebElement applicationForBackRound;
    @FindBy(css = ".col-6 .frame")
    private List<WebElement> appContainer;//editCampaignSuccessErrorMessage
    @FindBy(id = "editCampaignSuccessErrorMessage")
    private WebElement isLayoutSavedIndicator;
    @FindBy(id = "navItem1")
    private WebElement designerLink;
    @FindBy(xpath = "//button[contains(text(), 'Delete')]")
    private List<WebElement> deleteAppsFromFrames;
    @FindBy(className = "applications-container")
    private WebElement backRoundAppsContainer;
    @FindBy(className = "close")
    private WebElement closeLayoutFrame;

    public CampaignDesigner(WebDriver driver) {
        super(driver);
    }

    public void setUpCampaign(StateType stateType, LayoutType layoutType, String screenSize,boolean isBackRound){
        int timeOut = 0;
     if (!isBackRound){
         do {
             isElementPresent(screenSizeDdl);
             screenSizeDdl.sendKeys(screenSize);
             timeOut ++;
         }while (!screenSizeDdl.getAttribute("value").equals(screenSize.replace(".","")) && timeOut < 5);
     }
        switch (stateType){
            case DEFAULT:
                if (!isBackRound) {
                    defaultLink.click();
                }
                isElementPresent(layoutBtn);
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        if (!isBackRound){
                            setUpContainer(1, screenSize);
                        }else {
                            if (isElementPresent(closeLayoutFrame)) {
                                closeLayoutFrame.click();
                            }
                            deletAppsFromFrame();
                            setUpBackRoundApps(1,screenSize);
                        }
                        break;
                    case LAYOUT_2:
                        setUpContainer(2,screenSize);
                        break;
                    case LAYOUT_3:
                        setUpContainer(3,screenSize);
                        break;
                    case LAYOUT_4:
                        setUpContainer(4,screenSize);
                        break;
                    default:
                        ////
                        break;
                }
                break;
            case IDLE:
                idleLink.click();
                isElementPresent(layoutBtn);
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        if (!isBackRound){
                            setUpContainer(1, screenSize);
                        }else {
                            if (isElementPresent(closeLayoutFrame)) {
                                closeLayoutFrame.click();
                            }
                            deletAppsFromFrame();
                            setUpBackRoundApps(1,screenSize);
                        }
                        break;
                    case LAYOUT_2:
                        setUpContainer(2,screenSize);
                        break;
                    case LAYOUT_3:
                        setUpContainer(3,screenSize);
                        break;
                    case LAYOUT_4:
                        setUpContainer(4,screenSize);
                        break;
                    default:
                        ////
                        break;
                }
                break;
            case FUELING:
                fuelingLink.click();
                isElementPresent(layoutBtn);
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        if (!isBackRound){
                            setUpContainer(1, screenSize);
                        }else {
                            if (isElementPresent(closeLayoutFrame)) {
                                closeLayoutFrame.click();
                            }
                            deletAppsFromFrame();
                            setUpBackRoundApps(1,screenSize);
                        }
                        break;
                    case LAYOUT_2:
                        setUpContainer(2,screenSize);
                        break;
                    case LAYOUT_3:
                        setUpContainer(3,screenSize);
                        break;
                    case LAYOUT_4:
                        setUpContainer(4,screenSize);
                        break;
                    default:
                        ////
                        break;
                }
                break;
            case PAYMENT:
                paymentLink.click();
                isElementPresent(layoutBtn);
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        if (!isBackRound){
                            setUpContainer(1, screenSize);
                        }else {
                            if (isElementPresent(closeLayoutFrame)) {
                                closeLayoutFrame.click();
                            }
                            deletAppsFromFrame();
                            setUpBackRoundApps(1,screenSize);
                        }
                        break;
                    case LAYOUT_2:
                        setUpContainer(2,screenSize);
                        break;
                    case LAYOUT_3:
                        setUpContainer(3,screenSize);
                        break;
                    case LAYOUT_4:
                        setUpContainer(4,screenSize);
                        break;
                    default:
                        ////
                        break;
                }
                break;
            case POST_FUELING:
                postFuelingtLink.click();
                isElementPresent(layoutBtn);
                layoutBtn.click();
                switch (layoutType){
                    case LAYOUT_1:
                        if (!isBackRound){
                            setUpContainer(1, screenSize);
                        }else {
                            if (isElementPresent(closeLayoutFrame)) {
                                closeLayoutFrame.click();
                            }
                            deletAppsFromFrame();
                            setUpBackRoundApps(1,screenSize);
                        }
                        break;
                    case LAYOUT_2:
                        setUpContainer(2,screenSize);
                        break;
                    case LAYOUT_3:
                        setUpContainer(3,screenSize);
                        break;
                    case LAYOUT_4:
                        setUpContainer(4,screenSize);
                        break;
                    default:
                        ////
                        break;
                }
                break;
            default:
                ////
                break;
        }
        isElementPresent(saveBtn);
        saveBtn.click();
    }
    public boolean isSaveSucceeded(){
        return isLayoutSavedIndicator.getText().toLowerCase().contains("Successfully".toLowerCase());
    }
    public boolean getNumOfDraggedApps(int expectedApps){
        boolean res = true;
        int i;
        for(i = 0; i < expectedApps; i++){
            if(!appContainer.get(i).getCssValue("background-content").contains("openAppStore/webapi/storage")){
                res = false;
            }
        }
        return res;
    }
    private void setUpContainer(int numOfApps,String screenSize){
        if (screenSize.equals(ScreenSize.SIZE_15_6)){
            isElementPresent(layoutBox1.get(numOfApps));
            layoutBox1.get(numOfApps).click();
        }
        for (int i = 0; i < numOfApps; i++){
            SeleniumUtils.dragAndDrop(webDriver,applicationForLayout, appContainer.get(i));
        }
    }
    private void deletAppsFromFrame(){
        wait(WAIT);
        for (WebElement element : deleteAppsFromFrames){
            //element.click();
            deleteAppsFromFrames.get(0).click();
        }
    }
    private void setUpBackRoundApps(int numOfApps,String screenSize){
        SeleniumUtils.dragAndDrop(webDriver,applicationForBackRound, backRoundAppsContainer);
    }
    public int getNumOfDeleteButtons(){
       return webDriver.findElements(By.xpath("//button[contains(text(), 'Delete')]")).size();
    }
}
