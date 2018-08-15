package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class User extends PageObject {

    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement userPassword;
    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    WebElement loginBtn;
    @FindBy(className = "float-right")
    WebElement logoutBtn;

    public User(WebDriver driver) {
        super(driver);
    }
    public HomePage login(String userName,String password,boolean isAdmin){
        this.userName.clear();
        this.userName.sendKeys(userName);
        this.userPassword.clear();
        this.userPassword.sendKeys(password);
        this.loginBtn.click();
        if (isAdmin){
            //return admin page
        }
        return new HomePage(webDriver);
    }
    public void logout(){
        logoutBtn.click();
    }
}
