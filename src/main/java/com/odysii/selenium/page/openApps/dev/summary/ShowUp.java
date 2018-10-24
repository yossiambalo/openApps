package com.odysii.selenium.page.openApps.dev.summary;

import com.odysii.selenium.page.openApps.dev.AppVersion;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShowUp extends PageObject {

    @FindBy(id = "nextButton")
    private WebElement nextBtn;
    @FindBy(id = "finishButton")
    private WebElement finishBtn;
    @FindBy(xpath = "//span[contains(text(), 'Marketing')]")
    private WebElement marketing;
    @FindBy(id = "editAppNewVersion")
    private WebElement appVersions;
    @FindBy(xpath = "//span[contains(text(), 'Submission History')]")
    private WebElement submissionHistory;
    @FindBy(xpath = "//span[contains(text(), 'Statistics')]")
    private WebElement statistics;
    @FindBy(className = "block-item-menu-icon")
    private WebElement appVersionMenu;
    @FindBy(xpath =  "//button[contains(@id, 'certifyVersion')]")
    private WebElement certifyBtn;
    @FindBy(xpath =  "//button[contains(@id, 'editVersion')]")
    private WebElement editAppBtn;
    @FindBy(xpath = "//button[contains(@id, 'goLiveVersion')]")
    private WebElement addToAppStore;
    private String appStatusDivs =  "[class~=text-body-small]";
    @FindBy(className = "cx-item-block")
    private List<WebElement> versionDivs;

    public ShowUp(WebDriver driver) {
        super(driver);
    }

    public Summary getSummary() {
        return new Summary(webDriver);
    }
    public Marketing getMarketing() {
        marketing.click();
        return new Marketing(webDriver);
    }
    public AppVersion getAppVersion() {
        isElementPresent(appVersions);
        appVersions.click();
        return new AppVersion(webDriver);
    }
    public SubmissionHistory getSubmissionHistory() {
        submissionHistory.click();
        return new SubmissionHistory(webDriver);
    }
    public Statistics getStatistics() {
        statistics.click();
        return new Statistics(webDriver);
    }
    public void certify(){
        isElementPresent(appVersionMenu);
        this.appVersionMenu.click();
        this.certifyBtn.click();
        wait(WAIT);
        scrollDown(nextBtn);
        isElementPresent(nextBtn);
        this.nextBtn.click();
        isElementPresent(finishBtn);
        this.finishBtn.click();
    }
    public void edit(){
        int counter = 0;
        this.appVersionMenu.click();
        this.editAppBtn.click();

    }
    public void editApp(Summary summary){
        this.appVersionMenu.click();
        this.editAppBtn.click();
        summary.editSummary();
    }
    public void addApplicationToStore(){
        this.appVersionMenu.click();
        this.addToAppStore.click();
    }
    public String getStatus(){
        isElementPresent(versionDivs.get(0));
       return this.versionDivs.get(0).findElements(By.cssSelector(appStatusDivs)).get(0).getText();
    }
}
