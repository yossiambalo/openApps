package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Scheduling extends PageObject {
    @FindBy(className = "//a[contains(@id, 'toggleListMain')]")
    private WebElement siteArea;
    @FindBy(css = ".row .mb-2")
    private List<WebElement> campaignRows;
    @FindBy(id = "siteSelectionAccordion")
    private String siteSelectionAccordion;
    @FindBy(className = "dropdown")
    private List<WebElement> campaignListMenuBtn;
    @FindBy(id = "editCampaignSchedule")
    private List<WebElement> editCampaignSchedule;
    @FindBy(id = "schedulingDeployButton")
    private WebElement schedulingDeployButton;
    @FindBy(className = "footer-message")
    private WebElement footerMessage;

    public Scheduling(WebDriver driver) {
        super(driver);
    }

    public boolean deployToAll(int areaIndex){
        isElementPresent(campaignRows.get(0));
        campaignRows.get(0).click();
        isElementPresent(siteArea);
        WebElement areaElement = webDriver.findElement(By.id("toggleListMain"+areaIndex));
        List<WebElement> areaChildElements = webDriver.findElements(By.xpath("//a[contains(@id,'toggleListNested"+areaIndex+"')]"));
        if (!areaChildElements.get((areaChildElements.size() - 1)).isDisplayed()){
            //expand all
            expandAll(areaElement,areaChildElements);
            //list chckboxs
            List<WebElement> checkBoxs = webDriver.findElements(By.xpath("//i[contains(@id,'listNestedCheckAll"+areaIndex+"')]"));
            //check checkbox
            WebElement checkBox = checkBoxs.get(checkBoxs.size() - 1);
            isElementPresent(checkBox);
            if (!checkBox.getAttribute("class").contains("check")){
                checkBox.click();

            }

        }

        isElementPresent(schedulingDeployButton);
        schedulingDeployButton.click();
        return footerMessage.getText().contains("Finished deploying campaign");
    }

    private void expandAll(WebElement areaElement, List<WebElement> areaChildElements) {
        isElementPresent(areaElement);
        boolean isExpanded = Boolean.valueOf(areaElement.getAttribute("aria-expanded"));
        if (!isExpanded){
            areaElement.click();
            for (int i = 0; i < areaChildElements.size() - 1; i++){
                isElementPresent(areaChildElements.get(i));
                if (!Boolean.valueOf(areaChildElements.get(i).getAttribute("aria-expanded"))){
                    areaChildElements.get(i).click();
                }
            }
        }
    }
}