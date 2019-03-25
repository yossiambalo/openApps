package com.odysii.selenium.page.openApps.admin.helper;

public enum  OrganizationType {
    EXXONMOBILI(1),SHELL(2),SPRIN_MART(3),OPEN_APPS(4),ODYSII_QA(5),OPEN_APPS_ADMIN(6),GILBARCO(7),IMPULSE_CUSTOMER(8);
    private int index;

    OrganizationType(int index){
        this.index = index;
    }
    public int getIndex(){
        return index;
    }
}
