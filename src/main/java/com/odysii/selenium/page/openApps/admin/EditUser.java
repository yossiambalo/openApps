package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.OrganizationType;
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
    @FindBy(id = "organizationOverrideButton")
    WebElement organizationOverrideCheckBox;
    @FindBy(id = "organizationOverrideSelect")//confirmationModalUserEditAgreeButton
    WebElement organizationOverrideSelect;
    @FindBy(id = "confirmationModalUserEditAgreeButton")
    WebElement confirmationModalUserEditAgreeButton;

    public EditUser(WebDriver driver){
        super(driver);
    }
    public void edit(RoleType roleType, List<String> retailers){
        isElementPresent(userRoleDDL);
        Select dropdown = new Select(userRoleDDL);
        dropdown.selectByIndex(roleType.getIndex());
        if (retailers != null){
            //clean existing delegations
            List<WebElement> selectedDelegations = webDriver.findElement(By.className("multiselect-dropdown")).findElements(By.tagName("a"));
            for (WebElement e : selectedDelegations){
                e.click();
            }
            wait(WAIT);
            webDriver.findElement(By.className("dropdown-btn")).findElement(By.className("dropdown-down")).click();
            //add delegations
            for (String retailer : retailers){
                webDriver.findElement(By.xpath("//div[contains(text(), '"+retailer+"')]")).click();

            }
        }
        editUserButtonSave.click();
    }
    public void edit(RoleType roleType, List<String> retailers, OrganizationType organizationType){
        if (!isElementPresent(organizationOverrideSelect)){
            organizationOverrideCheckBox.click();
        }
        Select orgDDL = new Select(organizationOverrideSelect);
        orgDDL.selectByIndex(organizationType.getIndex());
        isElementPresent(userRoleDDL);
        Select dropdown = new Select(userRoleDDL);
        dropdown.selectByIndex(roleType.getIndex());
        if (retailers != null){
            List<WebElement> selectedDelegations = webDriver.findElement(By.className("multiselect-dropdown")).findElements(By.tagName("a"));
            for (WebElement e : selectedDelegations){
                e.click();
            }
            wait(WAIT);
            webDriver.findElement(By.className("dropdown-btn")).findElement(By.className("dropdown-down")).click();
            for (String retailer : retailers){
                webDriver.findElement(By.xpath("//div[contains(text(), '"+retailer+"')]")).click();

            }
        }
        editUserButtonSave.click();
        if (isElementPresent(confirmationModalUserEditAgreeButton)) {
            confirmationModalUserEditAgreeButton.click();
        }
    }
}
