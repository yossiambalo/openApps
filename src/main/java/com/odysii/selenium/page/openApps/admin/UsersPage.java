package com.odysii.selenium.page.openApps.admin;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Set;

public class UsersPage extends PageObject {

    @FindBy(className = "card-body")
    private List<WebElement> userList;
    @FindBy(id = "makeSearch")
    private WebElement makeSearchBtn;
    @FindBy(id = "editUser")
    WebElement editUserBtn;
    @FindBy(id = "newUserButton")
    WebElement addNewUser;
    @FindBy(id ="newUserButton")
    WebElement newUserButtonClick;


    public UsersPage(WebDriver driver) {
        super(driver);
    }
    public EditUser getUser(String developerEmail){
        boolean flag = false;
        int counter = 0;
        isElementPresent(makeSearchBtn);
        for (WebElement e : userList){
            if(e.findElement(By.className("text-truncate")).getText().trim().equals(developerEmail)){
                webDriver.findElement(By.id("editUser"+ counter)).click();
                flag = true;
                break;
            }
            counter ++;
        }
        if (!flag){
            throw new WebDriverException("Developer with name: "+developerEmail+" not found, please make sure developer name exist!");
        }
         return new EditUser(webDriver);
    }

    public AddUser getAddNewUserPage(){
        newUserButtonClick.click();

        return new AddUser(webDriver);
    }

}
