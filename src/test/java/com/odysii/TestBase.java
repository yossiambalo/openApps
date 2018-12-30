package com.odysii;

import java.io.File;

import com.odysii.selenium.page.openApps.User;
import com.odysii.selenium.page.openApps.UserType;
import com.odysii.selenium.page.openApps.admin.AdminPage;
import com.odysii.selenium.page.openApps.admin.SupportTicket;
import com.odysii.selenium.page.openApps.dev.*;
import com.odysii.selenium.page.openApps.dev.summary.ApplicationStatus;
import com.odysii.selenium.page.openApps.dev.summary.ShowUp;
import com.odysii.selenium.page.openApps.dev.summary.Summary;
import com.odysii.selenium.page.openApps.retailer.RetailerHomePage;
import com.odysii.selenium.page.util.DriverManager;
import com.odysii.selenium.page.util.DriverType;
import com.odysii.selenium.page.util.RequestHelper;
import org.openqa.selenium.*;
import org.testng.Assert;
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
import java.util.ArrayList;
import java.util.Set;

public class TestBase {
    public ArrayList<String> applicationIDToDelete = new ArrayList<>();
    public String category;
    public static String DEV_USER_NAME = "auto.dev.odysii@gmail.com";
    public final static String DEV_USER_PASS = "Aa123456";
    public final static String ADMIN_USER_NAME = "yossi.ambalo.odysii@gmail.com";
    public final static String ADMIN_USER_PASS = "Aa123456";
    public final static String RETAILER_USER_NAME = "auto.retail.odysii@gmail.com";
    public final static String RETAILER_USER_PASS = "Aa123456";
    public User user;
    public WebDriver driver;
    public RetailerHomePage retailerHomePage;
    public int appListBeforeAdding;
    public AdminPage adminPage;
    public DevHomePage devUser;
    AdminPage adminPage;
    public MyApps myApps;
    public java.util.List<WebElement> actualAppList;
    public int actualValue;
    protected final int WAIT = 7000;
    protected final String cancelID = "cancel-button";
    protected final String backTxt = "BACK";
    protected final String continueTxt = "CONTINUE";
    protected final String finishTxt = "FINISH";
    public static ExtentReports extent;
    public static ExtentTest logger;
    public final static String APP_CLASS_NAME = "card";
    public final String zipFile = "TH.zip";

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
    @AfterClass
    public void clean(){
        String token = null;
        Set<Cookie> allcookies = driver.manage().getCookies();
        for (Cookie cookie : allcookies){
            if (cookie.getName().equals("gvr-token")){
                token = cookie.toString();
                break;
            }
        }
        RequestHelper requestHelper = null;
        if (applicationIDToDelete != null){
            requestHelper = new RequestHelper();
            for (String appID: applicationIDToDelete){
                requestHelper.deleteRequest("http://odysiiopenappsqa.gilbarco.com:8080/openAppStore/webapi/application/"+appID,token);
            }
        }
        driver.quit();
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
        driver.get("http://odysiiopenappsqa.gilbarco.com:8080/openAppStore");
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
    public void prepareTest(String propFile , ApplicationStatus applicationStatus){
        user = new User(driver);
//        retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS, UserType.RETAILER);
//        //get number of live apps from retailer page
//        appListBeforeAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
//        user.logout();
        devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
        myApps = devUser.getMyAppsPage(driver);
        java.util.List<WebElement> appsList = driver.findElements(By.className(APP_CLASS_NAME));
        int appsSize = appsList.size();
        int expectedValue = appsSize+1;
        AppDetails appDetails = myApps.clickAddNewAppBtn();
        UploadCode uploadCode = appDetails.setUpAppDetailsFromPropFile(propFile);
        Marketing marketing = uploadCode.upload(zipFile);
        marketing.fillMarketing();
        wait(WAIT);
        actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
        actualValue = actualAppList.size();
        Assert.assertEquals(expectedValue,actualValue,"Failed to create a new application!");
       // Assert.assertTrue(myApps.getTitle(actualValue-1).toLowerCase().contains(appDetails.getAppTitle().toLowerCase()));
//        Assert.assertTrue(myApps.getDescription(actualValue-1).toLowerCase().contains(appDetails.getAppDescription().toLowerCase()));
        if (!applicationStatus.equals(ApplicationStatus.PRESUBMITTED)){
            setApplicationStatus(applicationStatus);
        }else {
            myApps = devUser.getMyAppsPage(driver);
            wait(WAIT);
            actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
            actualValue = actualAppList.size();
            ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
            setApplicationID();
        }
//       if (applicationStatus.equals(ApplicationStatus.LIVE)){
//           addAppToAppStore();
//       }
    }
    private void setApplicationStatus(ApplicationStatus applicationStatus){
        try {
            myApps = devUser.getMyAppsPage(driver);
            wait(WAIT);
            actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
            actualValue = actualAppList.size();
            ShowUp showUp = myApps.showUp(actualAppList.get(actualValue-1));
            setApplicationID();
            wait(WAIT);
            //Assert.assertEquals(ApplicationStatus.SUBMITTED.getStatus(),showUp.getStatus().trim());
            //Summary summary = new Summary(driver);
            //showUp.editApp(summary);
            showUp.certify();
            if (ApplicationStatus.SUBMITTED.equals(applicationStatus)){
                return;
            }
            user.logout();
            //Admin approve
            adminPage = (AdminPage)user.login(ADMIN_USER_NAME,ADMIN_USER_PASS,UserType.ADMIN);
            SupportTicket supportTicket = adminPage.getSupportTickets();
            switch (applicationStatus){
                case CERTIFIED:
                    supportTicket.approve();
                    break;
                case REJECT:
                    supportTicket.rejectNoFee();
                    break;
                case LIVE:
                    supportTicket.approve();
                    default:
                        //do nothing
            }
            user.logout();
            //Valid certified
            devUser = (DevHomePage) user.login(DEV_USER_NAME,DEV_USER_PASS, UserType.DEVELOPER);
            myApps = devUser.getMyAppsPage(driver);
            actualAppList = driver.findElements(By.className(APP_CLASS_NAME));
            int counter = 0;
            do {
                wait(2000);
                counter++;
            }while (driver.findElements(By.className(APP_CLASS_NAME)).size() < actualAppList.size() && counter < 5);
            showUp =  myApps.showUp(actualAppList.size()-1);
//            Assert.assertEquals(showUp.getStatus().trim(),ApplicationStatus.CERTIFIED.getStatus());
            if (ApplicationStatus.LIVE.equals(applicationStatus)) {
                showUp.addApplicationToStore();
            }
//            Assert.assertEquals(showUp.getStatus().trim(),ApplicationStatus.LIVE.getStatus());
        }catch (Exception e){
            e.getMessage();
        }
    }
    private void addAppToAppStore(){
        try {
            //Valid app added to retailer store
            retailerHomePage = (RetailerHomePage) user.login(RETAILER_USER_NAME,RETAILER_USER_PASS,UserType.RETAILER);
            int appListAfterAdding = driver.findElements(By.className(APP_CLASS_NAME)).size();
            Assert.assertEquals(appListAfterAdding,appListBeforeAdding + 1);
        }catch (Exception e){
            e.getMessage();
        }finally {
            user.logout();
        }
    }
    public void setApplicationID() {
        applicationIDToDelete.add( driver.getCurrentUrl().split("my-apps/")[1].split("/")[0]);
    }
}