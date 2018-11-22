package com.odysii;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.odysii.selenium.page.util.DriverManager;
import com.odysii.selenium.page.util.DriverType;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.server.ExportException;

public class TestBase {
    public WebDriver driver;
    protected final int WAIT = 7000;
    protected final String cancelID = "cancel-button";
    protected final String backTxt = "BACK";
    protected final String continueTxt = "CONTINUE";
    protected final String finishTxt = "FINISH";
    public static ExtentHtmlReporter htmlReporter;
    public static ExtentReports extent;
    public static ExtentTest test;
    public static String getHostName() {
        String hostName = "";
        try {
            hostName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return hostName;
    }
    @BeforeSuite
    public void setUp()
    {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/OpenApps_Automation_Report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        String userName = System.getProperty("user.name");
        String Os = System.getProperty("os.name");
        extent.setSystemInfo("OS", Os);
        extent.setSystemInfo("Host Name", getHostName());
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User Name", userName);

        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("AutomationTesting.in Demo Report");
        htmlReporter.config().setReportName("Open Apps Automation Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    @AfterMethod
    public void getResult(ITestResult result)
    {
        if(result.getStatus() == ITestResult.FAILURE)
        {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
            test.fail(result.getThrowable());
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
        }
        else
        {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }
    }


    @AfterClass
    public void tearDownExt(){
        driver.quit();
    }
    protected void wait(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown()
    {
        extent.flush();
    }


    @Parameters("browser")
    @BeforeClass
    public void init(String browser){
        switch (browser){
            case "chrome":
                driver = DriverManager.getWebDriver(DriverType.CHROME);
                break;
            case "edge":
                driver = DriverManager.getWebDriver(DriverType.EDGE);
                break;
            case "firefox":
                driver = DriverManager.getWebDriver(DriverType.FIREFOX);
                break;
            case "ie":
                driver = DriverManager.getWebDriver(DriverType.IE);
                break;
            default:
        }
        driver.get("http://openappsqa.tveez.local:8080/openAppStore");
    }
    protected boolean isElementExist(By by){
        boolean res = true;
        try{
            driver.findElement(by);
        }catch (NoSuchElementException e){
            res = false;
        }
        return res;
    }
    protected void pageUpDown(boolean down){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        if (down) {
            robot.keyPress(KeyEvent.VK_PAGE_DOWN);
        } else {
            robot.keyPress(KeyEvent.VK_PAGE_UP);
        }
        wait(3000);
        robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
    }
    protected void scrollDown(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
