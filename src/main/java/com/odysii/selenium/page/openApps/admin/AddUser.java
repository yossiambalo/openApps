package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddUser extends PageObject {

    @FindBy(id = "userName")
    WebElement userNameField;
    @FindBy(id = "userEmail")
    WebElement emailAddress;
    @FindBy(id = "userEditRole")
    WebElement roleDropDown;
    @FindBy(id = "editUserButtonSave")
    WebElement saveButton;
    @FindBy(id = "newUserButton")
    WebElement addNewUserBtn;
    @FindBy(id = "organizationOverrideButton")
    WebElement buttonOFOrganization;
    @FindBy(id = "confirmationModalUserEditAgreeButton")
    WebElement areYouSureMessageConfirm;
    @FindBy(id = "confirmationModalUserEditCancelButton")
    WebElement getAreYouSureMessageCancel;
    @FindBy(id = "BackNavigationButton")
    WebElement BackNavigationButton;


    public AddUser(WebDriver driver) {

        super(driver);
    }

    public void addNewUser(String fillUserName, String fillEmailAddress,String userRole,boolean clickOnDelegation, String delegations) {
        userNameField.sendKeys(fillUserName);
        emailAddress.sendKeys(fillEmailAddress);
        roleDropDown.sendKeys(userRole);
        if (clickOnDelegation == true){
            WebElement delegationDropDown = webDriver.findElement(By.className("dropdown-btn"));
            delegationDropDown.click();
            WebElement delegationsOptions = webDriver.findElement(By.xpath("//div[contains(text(), '"+delegations+"')]"));
            delegationsOptions.click();
//            wait(WAIT);
            delegationDropDown.sendKeys(delegations);
        }

        saveButton.click();

    }

    public void addNewUserWithOrgOverride(String fillUserName, String fillEmailAddress, boolean useOrganizationOverride,String orgOverride, String userRole, boolean clickOnDelegation, String delegations, boolean areYouSureMessage){
        userNameField.sendKeys(fillUserName);
        emailAddress.sendKeys(fillEmailAddress);

        if (useOrganizationOverride == true) {
            buttonOFOrganization.click();
        }
        Select dropDownOrgOverride = new Select(webDriver.findElement(By.id("organizationOverrideSelect")));
        dropDownOrgOverride.selectByVisibleText(orgOverride);
        roleDropDown.sendKeys(userRole);
        if (clickOnDelegation == true){
            WebElement delegationDropDown = webDriver.findElement(By.className("dropdown-btn"));
            delegationDropDown.click();
            WebElement delegationsOptions = webDriver.findElement(By.xpath("//div[contains(text(), '"+delegations+"')]"));
            delegationsOptions.click();
//            wait(WAIT);
            delegationDropDown.sendKeys(delegations);
        }
        saveButton.click();
        wait(WAIT);
        if (areYouSureMessage == true){
            areYouSureMessageConfirm.click();
        }
        else {
            getAreYouSureMessageCancel.click();
        }
    }

    public void backToUsersPage(){
       isElementPresent(BackNavigationButton);
        BackNavigationButton.click();
    }

}