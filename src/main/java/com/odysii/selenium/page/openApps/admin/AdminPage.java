package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends PageObject {

    @FindBy(id = "navItem0")
    private WebElement supportTicketsLink;
    @FindBy(id = "navItem1")
    private WebElement statisticsLink;
    @FindBy(id = "navItem2")
    private WebElement userPageLink;
    @FindBy(id = "navItem3")
    private WebElement retailersPageLink;
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
    public SupportTicket getSupportTicketsLink() {
        isElementPresent(supportTicketsLink);
        this.supportTicketsLink.click();
        return new SupportTicket(webDriver);
    }
    public SupportTicket getTicket(int index) {
        webDriver.findElement(By.id(TICKET_ID_FREFIX+index+"0")).click();
        return new SupportTicket(webDriver);

    }
    public UsersPage getUsersPage(){
        isElementPresent(this.userPageLink);
        this.userPageLink.click();
        return new UsersPage(webDriver);
    }
    public RetailersPage getRetailersPage(){
        isElementPresent(this.retailersPageLink);
        this.retailersPageLink.click();
        return new RetailersPage(webDriver);
    }
    public String getSupportTicketsText(){
        return supportTicketsLink.findElement(By.tagName("span")).getText();
    }
    public String getStatisticsText(){
        return statisticsLink.findElement(By.tagName("span")).getText();
    }
    public String getUsersText(){
        return userPageLink.findElement(By.tagName("span")).getText();
    }
    public String getRetailersText(){
        return retailersPageLink.findElement(By.tagName("span")).getText();
    }
}
