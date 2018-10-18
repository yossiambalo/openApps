package com.odysii.selenium.page.openApps.amin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupportTicket extends PageObject {
    @FindBy(className = "btn-group-toggle")
    private WebElement sortBtn;
    @FindBy(xpath = "//button[contains(text(), 'Creation Time')]")
    private WebElement creationTimeBtn;//ticket00
    @FindBy(id = "ticket00")
    private WebElement ticket;
    @FindBy(id = "adminTicketOpen")
    private WebElement openTicket;//adminTicketApproved
    @FindBy(id = "adminTicketApproved")
    private WebElement approveTicket;//finishButton
    @FindBy(id = "finishButtonAdminTicket")
    private WebElement finishButton;
    @FindBy(id = "adminTicketReject")
    private WebElement rejectTicketNoFee;
    @FindBy(id = "adminTicketRejectFee")
    private WebElement rejectTicketWithFee;

    public SupportTicket(WebDriver driver) {
        super(driver);
    }
    public void approve(){
       this.ticket.click();
       //this.openTicket.click();
       this.approveTicket.click();
       this.finishButton.click();
    }
    public void rejectNoFee(){
        this.ticket.click();
        //this.openTicket.click();
        this.rejectTicketNoFee.click();
        this.finishButton.click();
    }
    public void rejectWithFee(){
        this.ticket.click();
        //this.openTicket.click();
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
}
