package com.odysii.selenium.page.openApps;

import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.dev.DevHomePage;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class User extends PageObject {

    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement userPassword;
    @FindBy(id = "kc-login")
    WebElement loginBtn;
    @FindBy(id = "user-popover")//btn-xs
    WebElement logoutBtn;
    @FindBy(xpath = "//button[contains(text(), 'LOG OUT')]")
    WebElement logOutPopUp;
    public User(WebDriver driver) {
        super(driver);
    }

    public Object login(String userName,String password,UserType userType){
        Object object = null;
        if (!isElementPresent(this.userName)){
            logout();
        }
        this.userName.clear();
        this.userName.sendKeys(userName);
        this.userPassword.clear();
        this.userPassword.sendKeys(password);
        this.loginBtn.click();
        switch (userType){
            case DEVELOPER:
               object = new DevHomePage(webDriver);
               break;
            case RETAILER:
                object = new RetailerHomePage(webDriver);
                break;
            case ADMIN:
                object = new AdminPage(webDriver);
                break;
                default:
                    //
        }
        return object;
    }
    public void logout(){
        try {
            isElementPresent(logoutBtn);
            logoutBtn.click();
            isElementPresent(By.id("user-popover-content"));
            webDriver.findElement(By.id("user-popover-content")).findElements(By.className("btn-xs")).get(1).click();
        }catch (WebDriverException exception){
            exception.getMessage();
        }
    }
}
