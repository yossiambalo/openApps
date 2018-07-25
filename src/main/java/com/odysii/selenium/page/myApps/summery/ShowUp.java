package com.odysii.selenium.page.myApps.summery;

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

    public Summery getSummary() {
        this.summary.click();
        return new Summery(webDriver);
    }
     //ToDo: create Marketing class and return it.
    public WebElement getMarketing() {
        return marketing;
    }
    //ToDo: create AppVersions class and return it.
    public WebElement getAppVersions() {
        return appVersions;
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
