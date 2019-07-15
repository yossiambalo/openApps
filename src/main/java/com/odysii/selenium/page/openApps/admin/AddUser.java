package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddUser extends PageObject {
    WebDriver driver;

    @FindBy(id ="newUserButton")
    WebElement newUserButtonClick;
    @FindBy(id = "userName")
    WebElement userNameField;
    @FindBy(id = "userEmail")
    WebElement emailAddress;
    @FindBy(id = "userEditRole")
    WebElement roleDropDown;
    @FindBy(id = "editUserDelegations")
    WebElement delegationDropDown;
    @FindBy(id = "editUserButtonSave")
    WebElement saveButton;


    public void addNewUser(String fillUserName, String fillEmailAddress,String userRole, int delegations) {

        newUserButtonClick.click();
        userNameField.sendKeys(fillUserName);
        emailAddress.sendKeys(fillEmailAddress);
        roleDropDown.sendKeys(userRole);
        Select delegationDropDown = new Select(driver.findElement(By.className("dropdown-btn")));
        delegationDropDown.selectByIndex(2);
        saveButton.click();

    }


    public AddUser(WebDriver driver) {
        super(driver);
    }
}
