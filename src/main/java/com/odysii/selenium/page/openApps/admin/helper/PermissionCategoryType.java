package com.odysii.selenium.page.openApps.admin.helper;

public enum PermissionCategoryType {
    DEVELOPER("Developer"),PASSPORT("Passport"),STORE("Store"),LIBRARY("Library"),DESIGN_AND_DEPLOY("Design and Deploy"),
    CONTENT_MANAGER("Content manager"),ADMINSTRATOR("Admin");
    private String permission;

    PermissionCategoryType(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
