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
    @FindBy(css = ".card-display")
    private List<WebElement> dependenciesContainer;//cx-card-title
    @FindBy(css = ".cardsList .card-display")
    private List<WebElement> dependeciesDivs;
    @FindBy(id = "multiSelect_appId_11780")
    private WebElement selectAppVersionElm;
    @FindBy(css = ".col-7 .cx-card-title")
    private List<WebElement> appTitleName;

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
     * Enable application by title name
     * @param titleName : indicates which app application to enable
     */
    public void checkApplication(String titleName){
        if (isElementPresent(radioBtnElm.get(0))) {
            for (WebElement element : dependeciesDivs){
                if(titleName.trim().toLowerCase().equals(element.findElement(By.className("cx-card-title")).getText().trim().toLowerCase())){
                    element.findElement(By.className("switch")).click();
                    break;
                }
            }
        }
    }

    /**
     * Method: selects the second version by default
     */
    public void selectVersion(){
        if (isElementPresent(selectVersions.get(0))){
            selectVersions.get(0).click();
            selectVersions.get(0).findElements(By.tagName("li")).get(2).click();
        }
    }
    public UploadCode clickOnNextButton(){
        if (isElementPresent(nextButton)){
            nextButton.click();
        }
        return new UploadCode(webDriver);
    }
    public String getDependencyAppName(){
       isElementPresent(appTitleName.get(0));
        return appTitleName.get(0).getText();
    }
}
