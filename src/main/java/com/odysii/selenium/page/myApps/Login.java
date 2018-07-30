package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.HomePage;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends PageObject {

    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement userPassword;
    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    WebElement loginBtn;

    public Login(WebDriver driver) {
        super(driver);
    }
    public HomePage login(String userName,String password){
        this.userName.clear();
        this.userName.sendKeys(userName);
        this.userPassword.clear();
        this.userPassword.sendKeys(password);
        this.loginBtn.click();
        return new HomePage(webDriver);
    }
}
