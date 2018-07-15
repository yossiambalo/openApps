package com.odysii.selenium.page.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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
}
