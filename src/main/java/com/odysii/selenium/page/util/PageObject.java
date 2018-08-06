package com.odysii.selenium.page.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import sun.misc.IOUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class PageObject {
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
}
