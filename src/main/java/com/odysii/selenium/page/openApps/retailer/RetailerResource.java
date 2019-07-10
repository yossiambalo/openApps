package com.odysii.selenium.page.openApps.retailer;

import com.odysii.selenium.page.openApps.Resource;
import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;
import com.odysii.selenium.page.util.PageObject;
import org.openqa.selenium.WebDriver;

public class RetailerResource extends PageObject implements Resource {
    public RetailerResource(WebDriver driver) {
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
