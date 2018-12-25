package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.EnviromentType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class RetailersPage extends PageObject {

    @FindBy(className = "admin-retailer-row")
    private List<WebElement> retailerDivs;
    public RetailersPage(WebDriver driver) {
        super(driver);
    }
    public KeyMnagerPage editRetailer(String retailer){
        boolean flag = false;
        isElementPresent(retailerDivs.get(0));
        for (int i = 0; i < retailerDivs.size()-1; i++){
            if(retailerDivs.get(i).findElement(By.className("child_center")).getText().toLowerCase().equals(retailer.replaceAll("[\\s|\\u00A0]+", "").toLowerCase())){
                retailerDivs.get(i).findElement(By.className("block-item-menu-icon")).click();
                flag = true;
                webDriver.findElement(By.id("editRetailer"+(i+1))).click();
                break;
            }
        }
        if (!flag){
            throw new WebDriverException("Retailer with name: "+retailer+" not found, please make sure name is exist!");
        }
        return new KeyMnagerPage(webDriver);
    }
}
