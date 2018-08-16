package com.odysii.selenium.page.openApps.amin;

import com.odysii.selenium.page.openApps.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends User {

   @FindBy(xpath = "//span[contains(text(), 'Support Tickets')]")
   private WebElement supportTickets;
    public AdminPage(WebDriver driver) {
        super(driver);
    }
    public SupportTicket getSupportTickets() {
        this.supportTickets.click();
        return new SupportTicket(webDriver);
    }

}
