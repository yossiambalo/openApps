package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.openApps.Resource;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DevResource extends PageObject implements Resource {
    @FindBy(className = "card-body")
    private List<WebElement> resourceList;
    public DevResource(WebDriver driver) {
        super(driver);
    }

    public boolean isResourceExist(String resourceName) {
        return isResourceExist(resourceList,resourceName);
    }
    @Override
    public void download() {
        resourceList.get((resourceList.size() -1)).findElement(By.xpath("//button[contains(@id,'resourceDownload')]")).click();
    }
}
