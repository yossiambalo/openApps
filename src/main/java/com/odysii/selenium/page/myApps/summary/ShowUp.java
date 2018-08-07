package com.odysii.selenium.page.myApps.summary;

import com.odysii.selenium.page.myApps.AppVersion;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShowUp extends PageObject {

    @FindBy(xpath = "//span[contains(text(), 'Summary')]")
    private WebElement summary;
    @FindBy(xpath = "//span[contains(text(), 'Marketing')]")
    private WebElement marketing;
    @FindBy(xpath = "//span[contains(text(), 'App Versions')]")
    private WebElement appVersions;
    @FindBy(xpath = "//span[contains(text(), 'Submission History')]")
    private WebElement submissionHistory;
    @FindBy(xpath = "//span[contains(text(), 'Statistics')]")
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
    public SubmissionHistory getSubmissionHistory() {
        submissionHistory.click();
        return new SubmissionHistory(webDriver);
    }
    public Statistics getStatistics() {
        statistics.click();
        return new Statistics(webDriver);
    }
}
