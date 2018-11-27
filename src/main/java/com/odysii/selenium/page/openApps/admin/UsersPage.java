package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UsersPage extends PageObject {

    @FindBy(className = "admin-users-row")
    private List<WebElement> userList;
    @FindBy(id = "makeSearch")
    private WebElement makeSearchBtn;
    public UsersPage(WebDriver driver) {
        super(driver);
    }
    public void viewInstance(String developerName){
        boolean flag = false;
        isElementPresent(makeSearchBtn);
        for (WebElement e : userList){
            if(e.findElement(By.className("child_center")).getText().equals(developerName)){
                e.findElement(By.xpath("//button[contains(text(), 'View instance')]")).click();
                flag = true;
            }
        }
        if (!flag){
            throw new WebDriverException("Developer with name: "+developerName+" not found, please make sure developer name exist!");
        }
    }
}
