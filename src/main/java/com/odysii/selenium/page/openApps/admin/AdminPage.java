package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.Resource;
import com.odysii.selenium.page.openApps.ResourceType;
import com.odysii.selenium.page.openApps.dev.DevResource;
import com.odysii.selenium.page.openApps.retailer.RetailerResource;
import com.odysii.selenium.page.util.PageObject;
import com.odysii.selenium.page.util.RequestHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends PageObject {

    @FindBy(id = "navItem15")
    private WebElement supportTicketsLink;
    @FindBy(id = "navItem1")
    private WebElement statisticsLink;
    @FindBy(id = "navItem17")
    private WebElement userPageLink;
    @FindBy(id = "navItem18")
    private WebElement retailersPageLink;
    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement userPassword;
    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    WebElement loginBtn;
    @FindBy(className = "float-right")
    WebElement logoutBtn;
    @FindBy(id = "navItem19")
    private WebElement resourceLink;
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
    public AdminResource getResourcePage(){
        isElementPresent(this.resourceLink);
        this.resourceLink.click();
        return new AdminResource(webDriver);
    }
    public boolean isSupportTicketsEnabled(){
        return isLinkEnabled(supportTicketsLink);
    }
    public boolean isStatisticsEnabled(){
        return isLinkEnabled(statisticsLink);
    }
    public boolean isUsersEnabled(){
        return isLinkEnabled(userPageLink);
    }
    public boolean isRetailersEnabled(){
        return isLinkEnabled(retailersPageLink);
    }
    private boolean isLinkEnabled(WebElement element){
        isElementPresent(element);
        WebElement parentElm = (WebElement) ((JavascriptExecutor) webDriver).executeScript("return arguments[0].parentNode;", element);

        return (!parentElm.getAttribute("class").contains("disabled"));
    }

}
