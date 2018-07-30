package com.odysii.selenium.page.myApps.summary;

import com.odysii.selenium.page.myApps.AppVersion;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowUp extends PageObject {

    @FindBy(xpath = "//*[contains(text(), 'Summary')]")
    private WebElement summary;
    @FindBy(xpath = "//*[contains(text(), 'Marketing')]")
    private WebElement marketing;
    @FindBy(xpath = "//*[contains(text(), 'App Versions')]")
    private WebElement appVersions;
    @FindBy(xpath = "//*[contains(text(), 'Submission History')]")
    private WebElement submissionHistory;
    @FindBy(xpath = "//*[contains(text(), 'Statistics')]")
    private WebElement statistics;

    public ShowUp(WebDriver driver) {
        super(driver);
    }

    public Summary getSummary() {
        this.summary.click();
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
    //ToDo: create SubmissionHistory class and return it.
    public WebElement getSubmissionHistory() {
        return submissionHistory;
    }
    //ToDo: create Statistics class and return it.
    public WebElement getStatistics() {
        return statistics;
    }
}
