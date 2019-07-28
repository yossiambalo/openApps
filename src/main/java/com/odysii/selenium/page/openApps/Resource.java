package com.odysii.selenium.page.openApps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Stream;

public interface Resource {
    void download();

    default boolean isResourceExist(List<WebElement> resourceContainerList, String resourceName) {
        return resourceContainerList.stream().anyMatch(webElement -> {
            return webElement.findElement(By.className("col-lg-2")).getText().equals(resourceName);});
//        boolean res = false;
//        for (WebElement element : resourceContainerList){
//            if (element.findElement(By.className("col-lg-2")).getText().equals(resourceName)){
//                res = true;
//                break;
//            }
//        }
//        return res;
    }
}
