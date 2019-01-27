package com.odysii.selenium.page.openApps.dev;


import com.odysii.selenium.page.openApps.admin.SupportTicket;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DevHomePage extends PageObject{

    @FindBy(id = "navItem5")
    private WebElement myAppsLink;
    //@FindBy(xpath = "//*[contains(text(), 'Dashboard')]")
    @FindBy(id = "navItem4")
    private WebElement dashboardLink;
    @FindBy(id = "navItem6")
    private WebElement trasactionHistoryLink;
    @FindBy(id = "navItem7")
    private WebElement revenueReportLink;
    @FindBy(id = "navItem8")
    private WebElement supportTickets;
    @FindBy(id = "navItem9")
    private WebElement publicProfileLink;
    @FindBy(id = "navItem10")
    private WebElement encoreLink;
    @FindBy(id = "navItem12")
    private WebElement appStoreLink;
    @FindBy(name = "username")
    WebElement userName;
    @FindBy(name = "password")
    WebElement userPassword;
    @FindBy(xpath = "//button[contains(text(), 'Login')]")
    WebElement loginBtn;
    @FindBy(className = "float-right")
    WebElement logoutBtn;
    @FindBy(id = "navItem8")
    WebElement supportTicketLink;

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

    //DevHomePage Constructor
    public DevHomePage(WebDriver driver) {
        super(driver);
    }
    //get MyApps object
    public MyApps getMyAppsPage(WebDriver driver){
        isElementPresent(myAppsLink);
        this.myAppsLink.click();
        return new MyApps(driver);
    }
    //get Dashboard object
    public DashBoard getDashboardPage(WebDriver driver){
        this.dashboardLink.click();
        return new DashBoard(driver);
    }
    //get TransactionHistory object
    public TransactionHistory getTrasactionHistoryPage(WebDriver driver){
        this.trasactionHistoryLink.click();
        return new TransactionHistory(driver);
    }
    //get RevenueReport object
    public RevenueReport getRevenueReportPage(WebDriver driver){
        this.revenueReportLink.click();
        return new RevenueReport(driver);
    }
    //get SupportTickets object
    public SupportTickets getSupportTicketstPage(WebDriver driver){
        this.supportTickets.click();
        return new SupportTickets(driver);
    }
    //get PublicProfile object
    public PublicProfile getPublicProfilePage(WebDriver driver){
        this.publicProfileLink.click();
        return new PublicProfile(driver);
    }
    public SupportTicket getSupportTicket(){
        isElementPresent(supportTicketLink);
        supportTicketLink.click();
        return new SupportTicket(webDriver);
    }
    public String getDashboardText(){
        isElementPresent(dashboardLink);
        return dashboardLink.findElement(By.tagName("span")).getText();
    }
    public String getMyAppsText(){
        return myAppsLink.findElement(By.tagName("span")).getText();
    }
    public String getTransactionHistoryText(){
        return trasactionHistoryLink.findElement(By.tagName("span")).getText();
    }
    public String getRevenueReportText(){
        return revenueReportLink.findElement(By.tagName("span")).getText();
    }
    public String getSupportTicketText(){
        return supportTicketLink.findElement(By.tagName("span")).getText();
    }
    public String getPublicProfileText(){
        return publicProfileLink.findElement(By.tagName("span")).getText();
    }
    public String getEncoreText(){
        return encoreLink.findElement(By.tagName("span")).getText();
    }
    public String getAppStoreText(){
        return appStoreLink.findElement(By.tagName("span")).getText();
    }
}
