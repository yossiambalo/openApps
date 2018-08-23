package com.odysii.selenium.page.openApps.amin;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SupportTicket extends PageObject {
    @FindBy(className = "btn-group-toggle")
    private WebElement sortBtn;
    @FindBy(xpath = "//button[contains(text(), 'Creation Time')]")
    private WebElement creationTimeBtn;
    public SupportTicket(WebDriver driver) {
        super(driver);
    }
    public void approve(){
        sortBy(SORTBY.CREATION_TIME);
        //ToDo: approve here...!
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
