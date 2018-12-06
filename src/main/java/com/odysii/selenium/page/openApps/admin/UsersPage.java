package com.odysii.selenium.page.openApps.admin;
import com.odysii.selenium.page.openApps.admin.helper.RoleType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UsersPage extends PageObject {

    @FindBy(className = "admin-users-row")
    private List<WebElement> userList;
    @FindBy(id = "makeSearch")
    private WebElement makeSearchBtn;
    @FindBy(className = "mb-2")//dropdown-btn
    private WebElement userRoleDDL;
    @FindBy(className = "dropdown-btn")
    private WebElement delegationDDL;
    public UsersPage(WebDriver driver) {
        super(driver);
    }
    public void editUser(String developerName, RoleType roleType, List<String> delegations){
        boolean flag = false;
        isElementPresent(makeSearchBtn);
        for (WebElement e : userList){
            if(e.findElement(By.className("child_center")).getText().equals(developerName)){
                e.findElement(By.className("btn-reg")).click();
                flag = true;
                break;
            }
        }
        if (!flag){
            throw new WebDriverException("Developer with name: "+developerName+" not found, please make sure developer name exist!");
        }
        isElementPresent(userRoleDDL);
        Select dropdown = new Select(userRoleDDL);
        dropdown.selectByIndex(roleType.getIndex());
    }
}
