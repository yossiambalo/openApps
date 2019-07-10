package com.odysii.selenium.page.openApps.dev;

import com.odysii.selenium.page.openApps.Resource;
import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DevResource extends PageObject implements Resource {
    @FindBy(xpath = "//span[contains(text(), 'Resources')]")
    private WebElement resourceLink;
    public DevResource(WebDriver driver) {
        super(driver);
    }

    @Override
    public void addNewResource(String resourceName, String description, PermissionCategoryType permissionCategoryType) {

    }

    @Override
    public boolean deleteLastResource() {
        return false;
    }
}
