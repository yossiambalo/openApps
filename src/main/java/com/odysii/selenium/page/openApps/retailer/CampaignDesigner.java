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

    @FindBy(xpath = "//h5[contains(text(), 'SignTest')]")
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

    public void setUpCampaign(StateType stateType, LayoutType layoutType, int numOfApps, String screenSize, boolean isBackRound){
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
                dragAndDrop(isBackRound,numOfApps,screenSize);
                break;
            case IDLE:
                if (!isBackRound) {
                    idleLink.click();
                }
                dragAndDrop(isBackRound,numOfApps,screenSize);
                break;
            case FUELING:
                if (!isBackRound) {
                    fuelingLink.click();
                }
                dragAndDrop(isBackRound,numOfApps,screenSize);
                break;
            case PAYMENT:
                if (!isBackRound) {
                    paymentLink.click();
                }
                dragAndDrop(isBackRound,numOfApps,screenSize);
                break;
            case POST_FUELING:
                if (!isBackRound) {
                    postFuelingtLink.click();
                }
                dragAndDrop(isBackRound,numOfApps,screenSize);
                break;
            default:
                ////
                break;
        }
        isElementPresent(saveBtn);
        saveBtn.click();
    }

    private void dragAndDrop(boolean isBackRound,int numOfApps, String screenSize) {
        isElementPresent(layoutBtn);
        layoutBtn.click();
        if (!isBackRound){
            setUpContainer(numOfApps, screenSize);
        }else {
            if (isElementPresent(closeLayoutFrame)) {
                closeLayoutFrame.click();
            }
            deleteAppsFromFrame();
            setUpBackRoundApps(1,screenSize);
        }
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
    private void deleteAppsFromFrame(){
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
