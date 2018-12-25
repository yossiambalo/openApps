package com.odysii.selenium.page.openApps.admin.helper;

public enum RoleType {
     THIRD_PARTY(0);

    private int index;
    public int getIndex() {
        return index;
    }
    RoleType(int index) {
        this.index = index;
    }
}
