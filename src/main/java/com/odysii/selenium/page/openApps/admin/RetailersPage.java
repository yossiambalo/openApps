package com.odysii.selenium.page.openApps.admin;

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
    public void getEditPage(String retailer){
        boolean flag = false;
        isElementPresent(retailerDivs.get(0));
        for (WebElement e : retailerDivs){
            if(e.findElement(By.className("child_center")).getText().toLowerCase().equals(retailer.trim().toLowerCase())){
                e.findElement(By.className("col-auto")).click();
                flag = true;
                break;
            }
        }
        if (!flag){
            throw new WebDriverException("Retailer with name: "+retailer+" not found, please make sure name is exist!");
        }
    }
}
