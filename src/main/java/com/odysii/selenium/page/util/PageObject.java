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
         boolean res = true;
         try{
             webDriver.findElements(by);
         }catch (NoSuchElementException e){
             res = false;
         }
         return res;
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
        while (!element.isDisplayed() && counter < 5){
            wait(4000);
            counter ++;
        }
       return counter != 5;
    }
}
