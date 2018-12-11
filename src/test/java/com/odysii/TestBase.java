package com.odysii;

import java.io.File;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.util.DriverManager;
import com.odysii.selenium.page.util.DriverType;
import org.openqa.selenium.*;
import org.testng.annotations.*;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestBase {
    public String category;
    public final static String DEV_USER_NAME = "user";
    public final static String DEV_USER_PASS = "123456";
    public final static String ADMIN_USER_NAME = "admin";
    public final static String ADMIN_USER_PASS = "admin";
    public final static String RETAILER_USER_NAME = "retailer";
    public final static String RETAILER_USER_PASS = "123456";
    public User user;
    public WebDriver driver;
    protected final int WAIT = 7000;
    protected final String cancelID = "cancel-button";
    protected final String backTxt = "BACK";
    protected final String continueTxt = "CONTINUE";
    protected final String finishTxt = "FINISH";
    public static ExtentReports extent;
    public static ExtentTest logger;

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
        extent = new ExtentReports (System.getProperty("user.dir") +"/test-output/OpenApps_Automation_Report.html", true);
        String Os = System.getProperty("os.name");
        String userName = System.getProperty("user.name");
        extent.addSystemInfo("Host Name", getHostName())
                .addSystemInfo("Environment", "QA")
                .addSystemInfo("User Name", userName);
        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));

    }

    @AfterMethod
    public void getResult(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
            logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(LogStatus.PASS, "Test Case Passed " + result.getName());
            extent.endTest(logger);
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
    public boolean isElementPresent(WebElement element) {
        int counter = 0;
        try {
            while (!element.isDisplayed() && counter < 5){
                wait(4000);
                counter ++;
            }
        }catch (NoSuchElementException e){
            System.out.println(e.fillInStackTrace());
            return false;
        }
        if (counter == 5){
            return false;
        }
        wait(WAIT);
        return true;
    }
    @BeforeMethod
    public void handleTestMethodName(Method method)
    {
        logger = extent.startTest(method.getName()).assignCategory(this.category+" Tests");
    }
}