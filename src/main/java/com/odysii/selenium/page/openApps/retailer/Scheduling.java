package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Scheduling extends PageObject {
    @FindBy(className = "col-auto")
    private List<WebElement> campaigns;
    public Scheduling(WebDriver driver) {
        super(driver);
    }
    public void deployToDispenser(int campaignIndex){
        int indicator = 0;
        for(WebElement elm : campaigns){
            indicator ++;
            if (indicator - 1 == campaignIndex){
                elm.click();
                break;
            }
        }
        ///
    }
}
