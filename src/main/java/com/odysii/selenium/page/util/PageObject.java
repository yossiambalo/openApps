package com.odysii.selenium.page.util;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PageObject {
    protected final int WAIT = 4000;
    protected WebDriver webDriver;

    public PageObject(WebDriver driver) {
        this.webDriver = driver;
        PageFactory.initElements(driver, this);
    }

    public void wait(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isElementExist(By by) {
        int counter = 0;
        boolean res = true;
        Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        if ("microsoftedge".equals(browserName)){
            wait(WAIT);
        }
        while (webDriver.findElements(by).size() < 1 && counter < 1) {
            wait(4000);
            counter++;
        }

        if (counter == 1) {
            res = false;
        }
        wait(WAIT);
        return res;
    }

    protected String getFile(String fileName) {
        return System.getProperty("user.dir")+"\\src\\main\\resources\\"+fileName;
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

    public int scrollDown()
    {
        JavascriptExecutor js = ((JavascriptExecutor) webDriver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        return (int) (long) js.executeScript("return document.body.scrollHeight;");
    }

    public boolean isElementPresent(WebElement element) {
        int counter = 0;
        Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        if ("microsoftedge".equals(browserName)){
            wait(WAIT);
        }
        try {
            while (!element.isDisplayed() && counter < 10){
                wait(1000);
                counter ++;
            }
        }catch (NoSuchElementException e){
            e.getMessage();
            return false;
        }
       if (counter == 10){
           return false;
       }
       wait(WAIT);
       return true;
    }

    public boolean isElementPresent(By by) {
        try {
            webDriver.findElement(by);
        }catch (NoSuchElementException e){
            e.getMessage();
            return false;
        }
        wait(WAIT);
        return true;
    }

    protected void scrollDown(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
