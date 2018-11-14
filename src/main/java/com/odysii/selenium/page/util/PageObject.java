package com.odysii.selenium.page.util;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class PageObject {
    protected final int WAIT = 4000;
    protected WebDriver webDriver;
    public PageObject(WebDriver driver){
        this.webDriver = driver;
        PageFactory.initElements(driver,this);
    }
     public void wait(int miliseconds){
         try {
             Thread.sleep(miliseconds);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
     public boolean isElementExist(By by){
         int counter = 0;
         while (webDriver.findElements(by).size() < 1 && counter < 5){
             wait(4000);
             counter ++;
         }
         if (counter == 5){
             throw new ExplicitAssertionError("element not found");
         }
         wait(WAIT);
         return true;
     }
    protected String getFile(String fileName){

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return file.toString();
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
    protected void scrollDown(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
}
