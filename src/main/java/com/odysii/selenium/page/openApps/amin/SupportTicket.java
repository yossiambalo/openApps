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
    @FindBy(id = "adminTicketButtonOpen")
    private WebElement openTicket;//adminTicketApproved
    @FindBy(id = "adminTicketApproved")
    private WebElement approveTicket;//finishButton
    @FindBy(id = "finishButton")
    private WebElement finishButton;
    public SupportTicket(WebDriver driver) {
        super(driver);
    }
    public void approve(){
       this.ticket.click();
       this.openTicket.click();
       this.approveTicket.click();
       this.finishButton.click();
    }
    private void sortBy(SORTBY sortBy){
        this.sortBtn.click();
        switch (sortBy){
            case CREATION_TIME:
                this.creationTimeBtn.click();
                break;
                default:
        }
    }
}
