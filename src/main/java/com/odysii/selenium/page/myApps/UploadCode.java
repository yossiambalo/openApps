package com.odysii.selenium.page.myApps;

import com.odysii.selenium.page.util.PageObject;
import com.odysii.utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.Properties;

public class UploadCode extends PageObject{

    @FindBy(className = "custom-file-input")
    WebElement agreeAndUpload;
    @FindBy(id = "next-button")
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
