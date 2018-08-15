package com.odysii.selenium.page.myApps.summary;

import com.odysii.selenium.page.myApps.AppVersion;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowUp extends PageObject {

    @FindBy(id = "next-button")
    private WebElement nextBtn;
    @FindBy(id = "finish-button")
    private WebElement finishBtn;
    @FindBy(xpath = "//span[contains(text(), 'Marketing')]")
    private WebElement marketing;
    @FindBy(xpath = "//span[contains(text(), 'App Versions')]")
    private WebElement appVersions;
    @FindBy(xpath = "//span[contains(text(), 'Submission History')]")
    private WebElement submissionHistory;
    @FindBy(xpath = "//span[contains(text(), 'Statistics')]")
    private WebElement statistics;
    @FindBy(className = "block-item-menu-icon")
    private WebElement appVersionMenu;
    @FindBy(xpath = "//button[contains(text(), 'Certify')]")
    private WebElement certifyBtn;

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
    public AppVersion getAppVersions() {
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
        this.appVersionMenu.click();
        this.certifyBtn.click();
        wait(2000);
        pageUpDown(true);
        this.nextBtn.click();
        this.finishBtn.click();
    }
}
