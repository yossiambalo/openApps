package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.openApps.dev.Marketing;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadCode extends PageObject{

    @FindBy(className = "custom-file-input")
    WebElement agreeAndUpload;
    @FindBy(id = "nextButton")
    WebElement next;
    public UploadCode(WebDriver driver) {
        super(driver);
    }
    public Marketing upload(String zipFile){
        this.agreeAndUpload.sendKeys(getFile("application//"+zipFile));
        wait(2000);
        this.next.click();
        return new Marketing(webDriver);
    }
}
