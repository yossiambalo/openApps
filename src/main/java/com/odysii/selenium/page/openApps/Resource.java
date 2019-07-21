package com.odysii.selenium.page.openApps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public interface Resource {
    void download();

    default boolean isResourceExist(List<WebElement> resourceContainerList, String resourceName) {
        boolean res = false;
        for (WebElement element : resourceContainerList){
            if (element.findElement(By.className("col-lg-2")).getText().equals(resourceName)){
                res = true;
                break;
            }
        }
        return res;
    }
}
