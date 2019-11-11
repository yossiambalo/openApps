package com.odysii.selenium.page.openApps.admin;

import com.odysii.selenium.page.openApps.Resource;
import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AdminResource extends PageObject implements Resource {
    @FindBy(id = "newResourceButton")
    private WebElement newResourceButton;
    @FindBy(id = "displayName")
    private WebElement displayName;
    @FindBy(id = "description")
    private WebElement description;
    @FindBy(id = "resourceEditPermission")
    private WebElement selectResourcePermission;
    @FindBy(id = "resourceFileSummary")
    private WebElement inputUploadResource;
    @FindBy(id = "Save")
    private WebElement buttonSave;
    @FindBy(className = "card-body")
    private List<WebElement> resourceContainerList;
    @FindBy(xpath = "//button[contains(@id,'deleteResource')]")
    private List<WebElement> buttonDeleteResource;
    @FindBy(xpath = "//button[contains(@id,'editResource')]")
    private List<WebElement> buttonEditResource;
    @FindBy(xpath = "//a[contains(@id,'resourceDownload')]")
    private List<WebElement> resourceDownload;

    public AdminResource(WebDriver driver) {
        super(driver);
    }

    public void addNewResource(String resourceName, String description, PermissionCategoryType permissionCategoryType){
        isElementPresent(newResourceButton);
        newResourceButton.click();
        isElementPresent(displayName);
        displayName.sendKeys(resourceName);
        this.description.sendKeys(description);
        selectResourcePermission.sendKeys(permissionCategoryType.getPermission());
        inputUploadResource.sendKeys(getFile("code\\TH.zip"));
        int counter = 0;
        while (!buttonSave.isEnabled() && counter < 5){
            wait(1000);
            counter++;
        }
        if (counter == 5){
            throw new WebDriverException("Can not click save button: " + buttonSave);
        }
        buttonSave.click();
    }

    public void editResource(String resourceName,String newResourceName, String newDescription,PermissionCategoryType permissionCategoryType){
        WebElement element = resourceContainerList.stream().filter(webElement -> {
            return webElement.findElement(By.className("col-lg-2")).getText().equals(resourceName);
        }).findAny().orElse(null);
        WebElement menuBtnEl = element.findElement(By.className("dropdown"));
        scrollDown(menuBtnEl);
        menuBtnEl.click();
        wait(2000);
       buttonEditResource.stream().filter(WebElement::isDisplayed).findAny().orElse(null).click();
        wait(2000);
        isElementPresent(displayName);
        displayName.clear();
        displayName.sendKeys(newResourceName);
        description.clear();
        description.sendKeys(newDescription);
        selectResourcePermission.sendKeys(permissionCategoryType.getPermission());
        inputUploadResource.sendKeys(getFile("code\\TH.zip"));
        wait(2000);
        buttonSave.click();
    }

    public boolean deleteLastResource(){
        int lastResourceIndex = resourceContainerList.size() - 1;
        scrollDown();
        resourceContainerList.get(lastResourceIndex).findElement(By.className("dropdown")).click();
        wait(2000);
        buttonDeleteResource.get(lastResourceIndex).click();
        wait(2000);
        webDriver.findElement(By.id("Delete")).click();
        wait(3000);
        int newResourceContainerListSize = webDriver.findElements(By.className("card-body")).size();

        return newResourceContainerListSize == lastResourceIndex;
    }

    @Override
    public void download() {
        resourceDownload.get((resourceDownload.size() - 1)).click();
    }

    public boolean isResourceExist(String resourceName) {
        return isResourceExist(resourceContainerList,resourceName);

    }

}