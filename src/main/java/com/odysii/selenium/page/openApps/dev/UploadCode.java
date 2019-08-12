package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class UploadCode extends PageObject{

    @FindBy(id = "codeFile")
    WebElement agreeAndUpload;//codeFile
    @FindBy(id = "nextButton")
    WebElement next;
    @FindBy(id = "newAppUploadCode")
    WebElement uploadCodeBtn;
    @FindBy(id = "nextButton")
    List<WebElement> nexts;
    public UploadCode(WebDriver driver) {
        super(driver);
    }
    public Marketing upload(String zipFile){
        isElementPresent(uploadCodeBtn);
        this.agreeAndUpload.sendKeys(getFile("code\\" +zipFile));
        wait(5000);
        if (next.isDisplayed()) {
            this.next.click();
        }else {
            WebDriverWait wait = new WebDriverWait(webDriver, 10000);
            wait.until(ExpectedConditions.elementToBeClickable(nexts.get(1)));
            nexts.get(1).click();
        }
        return new Marketing(webDriver);
    }
}
