package com.odysii.selenium.page.openApps;

import com.odysii.selenium.page.openApps.admin.helper.PermissionCategoryType;

public interface Resource {
    void addNewResource(String resourceName, String description, PermissionCategoryType permissionCategoryType);
    boolean deleteLastResource();
}
