package com.odysii.selenium.page;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppStore extends PageObject{

    /**
     * ---------Start AppStore WebElements---------
     */
    @FindBy(xpath = "//*[contains(text(), 'My Apps')]")
    private WebElement myAppsLink;
    @FindBy(xpath = "//*[contains(text(), 'Dashboard')]")
    private WebElement dashboardLink;
    @FindBy(xpath = "//*[contains(text(), 'Transaction History')]")
    private WebElement trasactionHistory;
    @FindBy(xpath = "//*[contains(text(), 'Revenue Report')]")
    private WebElement revenueReport;
    @FindBy(xpath = "//*[contains(text(), 'Support Tickets')]")
    private WebElement supportTickets;
    @FindBy(xpath = "//*[contains(text(), 'Public Profile')]")
    private WebElement publicProfile;

    /**
     * ---------End AppStore WebElements---------
     */

    //AppStore Constructor
    public AppStore(WebDriver driver) {
        super(driver);
    }

    //get MyApps object
    public MyApps clickMyAppsLink(WebDriver driver){
        this.myAppsLink.click();
        return new MyApps(driver);
    }
    //get Dashboard object
    public  DashBoard clickDashboardLink(WebDriver driver){
        this.dashboardLink.click();
        return new DashBoard(driver);
    }
    //get TransactionHistory object
    public TransactionHistory clickTrasactionHistory(WebDriver driver){
        this.trasactionHistory.click();
        return new TransactionHistory(driver);
    }
    //get RevenueReport object
    public  RevenueReport clickRevenueReport(WebDriver driver){
        this.revenueReport.click();
        return new RevenueReport(driver);
    }
    //get SupportTickets object
    public  SupportTickets clickSupportTicketst(WebDriver driver){
        this.supportTickets.click();
        return new SupportTickets(driver);
    }
    //get PublicProfile object
    public  PublicProfile clickPublicProfile(WebDriver driver){
        this.publicProfile.click();
        return new PublicProfile(driver);
    }
}
