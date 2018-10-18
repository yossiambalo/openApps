package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends PageObject {

    @FindBy(xpath = "//span[contains(text(), 'Support Tickets')]")
    private WebElement supportTickets;
    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement userPassword;
    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    WebElement loginBtn;
    @FindBy(className = "float-right")
    WebElement logoutBtn;
    private final static String TICKET_ID_FREFIX = "ticket";

    public void login(String userName,String password){
        this.userName.clear();
        this.userName.sendKeys(userName);
        this.userPassword.clear();
        this.userPassword.sendKeys(password);
        this.loginBtn.click();
    }
    public void logout(){
        logoutBtn.click();
    }
    public AdminPage(WebDriver driver) {
        super(driver);
    }
    public SupportTicket getSupportTickets() {
        this.supportTickets.click();
        return new SupportTicket(webDriver);
    }
    public SupportTicket getTicket(int index) {
        webDriver.findElement(By.id(TICKET_ID_FREFIX+index+"0")).click();
        return new SupportTicket(webDriver);

    }

}
