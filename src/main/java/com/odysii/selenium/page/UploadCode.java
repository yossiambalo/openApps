package com.odysii.selenium.page;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadCode extends PageObject{

    @FindBy(xpath = "//*[contains(text(), ' AGREE AND UPLOAD')]")
    WebElement agreeAndUpload;
    @FindBy(xpath = "//*[contains(text(), 'NEXT')]")
    WebElement next;
    public UploadCode(WebDriver driver) {
        super(driver);
    }
    public Marketing upload(){
        this.agreeAndUpload.click();
        this.next.click();
        return new Marketing(webDriver);
    }
}
