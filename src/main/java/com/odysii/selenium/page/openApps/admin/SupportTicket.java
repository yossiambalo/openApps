package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.admin.helper.SortBy;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupportTicket extends PageObject {
    @FindBy(className = "btn-group-toggle")
    private WebElement sortBtn;
    @FindBy(xpath = "//button[contains(text(), 'Creation Time')]")////*[contains(@id,'_default-create-firstname')]
    private WebElement creationTimeBtn;
    @FindBy(id = "ticket00")
    private WebElement ticket;
    @FindBy(id = "adminTicketOpen")
    private WebElement openTicket;
    @FindBy(id = "adminTicketApproved")
    private WebElement approveTicket;//finishButton
    @FindBy(id = "finishButtonAdminTicket")
    private WebElement finishButton;
    @FindBy(id = "adminTicketReject")
    private WebElement rejectTicketNoFee;
    @FindBy(id = "adminTicketRejectFee")
    private WebElement rejectTicketWithFee;
    @FindBy(id = "BackNavigationButton")
    WebElement backNavigationButton;

    public SupportTicket(WebDriver driver) {
        super(driver);
    }
    public void approve(){
       this.ticket.click();
       isElementPresent(approveTicket);
       this.approveTicket.click();
       this.finishButton.click();
    }
    public void rejectNoFee(){
        this.ticket.click();
        isElementPresent(rejectTicketNoFee);
        this.rejectTicketNoFee.click();
        isElementPresent(finishButton);
        this.finishButton.click();
    }
    public void rejectWithFee(){
        this.ticket.click();
        isElementPresent(rejectTicketWithFee);
        this.rejectTicketWithFee.click();
        this.finishButton.click();
    }
    private void sortBy(SortBy sortBy){
        this.sortBtn.click();
        switch (sortBy){
            case CREATION_TIME:
                this.creationTimeBtn.click();
                break;
                default:
        }
    }
    public String getAppStatus(){
        isElementPresent(ticket);
        return ticket.findElement(By.className("px-4")).getText();
    }
    public void backToSupportTicket(){
        isElementPresent(backNavigationButton);
        backNavigationButton.click();
    }
}
