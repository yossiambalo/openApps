package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class EditUser extends PageObject {

    @FindBy(id = "userEditRole")
    private WebElement userRoleDDL;
    @FindBy(id = "editUserDelegations")//multiselect-dropdown
    private WebElement delegationDDL;
    @FindBy(id = "editUserButtonSave")
    WebElement editUserButtonSave;
    public EditUser(WebDriver driver){
        super(driver);
    }
    public void edit(RoleType roleType, List<String> retailers){
        isElementPresent(userRoleDDL);
        Select dropdown = new Select(userRoleDDL);
        dropdown.selectByIndex(roleType.getIndex());
        if (retailers != null){
            webDriver.findElement(By.className("dropdown-btn")).findElement(By.className("dropdown-down")).click();
            for (String retailer : retailers){
                webDriver.findElement(By.xpath("//div[contains(text(), '"+retailer+"')]")).click();
            }
        }
        editUserButtonSave.click();
    }
}
