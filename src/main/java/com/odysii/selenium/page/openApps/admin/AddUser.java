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
//    @FindBy(id = "editUserDelegations")
//    WebElement delegationDropDown;
    @FindBy(id = "editUserButtonSave")
    WebElement saveButton;

    public AddUser(WebDriver driver) {
        super(driver);
    }

    public void addNewUser(String fillUserName, String fillEmailAddress,String userRole, String delegations) {

        userNameField.sendKeys(fillUserName);
        emailAddress.sendKeys(fillEmailAddress);
        roleDropDown.sendKeys(userRole);
        WebElement delegationDropDown = webDriver.findElement(By.className("dropdown-btn"));
        delegationDropDown.click();
        WebElement delegationsOptions = webDriver.findElement(By.xpath("//div[contains(text(), '"+delegations+"')]"));
        delegationsOptions.click();
        wait(WAIT);
        delegationDropDown.sendKeys(delegations);
        saveButton.click();

    }

}
