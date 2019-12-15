package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Dependencies extends PageObject {

    @FindBy(id = "applicationSearchInput")
    private List<WebElement> applicationSearchInputElm;
    @FindBy(id = "multiSelect_step2")
    private WebElement filterByCategoriesElm;
    @FindBy(className = "switch")
    private List<WebElement> radioBtnElm;
    @FindBy(id = "multiSelect_appId_11780")
    private WebElement selectAppVersionElm;

    @FindBy(xpath = "//ng-multiselect-dropdown[contains(@id, 'multiSelect_appId_')]")
    private List<WebElement> selectVersions;
    @FindBy(id = "nextButton")
    private WebElement nextButton;

    public Dependencies(WebDriver driver) {
        super(driver);
    }

    public void searchApplication(String appName){
        if (isElementPresent(applicationSearchInputElm.get(1))){
            applicationSearchInputElm.get(1).sendKeys(appName);
            applicationSearchInputElm.get(1).sendKeys(Keys.ENTER);
        }else
            try {
                throw new Exception("Element not found!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
    }
    public void selectCategory(String categoryName){
        WebElement category = webDriver.findElement(By.xpath("//div[contains(text(), '"+categoryName+"')]"));
        ((JavascriptExecutor)webDriver).executeAsyncScript("arguments[0].click();",category);
    }

    /**
     * Enable the first application by default
     * For enable dynamic application consider to overload method to
     * take radio button index
     */
    public void checkApplication(){
        WebElement firstRadioBtn = radioBtnElm.get(0);
        if (!firstRadioBtn.getAttribute("class").contains("checked")){
            firstRadioBtn.click();
        }
    }

    /**
     * Method: selects the second version by default
     */
    public void selectVersion(){
        WebElement firstVersionElm = selectVersions.get(0);
        if (isElementPresent(firstVersionElm)){
            firstVersionElm.click();
            firstVersionElm.findElements(By.tagName("li")).get(2).click();
        }
    }
    public UploadCode clickOnNextButton(){
        if (isElementPresent(nextButton)){
            nextButton.click();
        }
        return new UploadCode(webDriver);
    }
}
