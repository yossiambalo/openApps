package com.odysii;

import com.odysii.selenium.DriverManager;
import com.odysii.selenium.DriverType;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG {

    @Test
    public void test(){
        WebDriver driver = DriverManager.getWebdriver(DriverType.CHROME);
        driver.get("https://google.com");
        Assert.assertEquals("","");
    }
}
