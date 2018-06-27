package com.odysii.selenium.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends PageObject {

    @FindBy(id="user_email")
    private WebElement email;
    @FindBy(id="user_password")
    private WebElement password;
    @FindBy(name="commit")
    private WebElement submitButton;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }
    public void enterCredentials(String userEmail, String password){
        this.email.clear();
        this.email.sendKeys(userEmail);
        this.password.clear();
        this.password.sendKeys(password);
    }
//    public ProjectPage submit(){
//        this.submitButton.click();
//        return new ProjectPage(webDriver);
//    }
}