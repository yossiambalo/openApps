package com.odysii.selenium.page.openApps.admin.helper;

public enum RetailerName {
    EXXONMOBIL("ExxonMobil"),
    SHELL("Shell"),
    SPRINT_MART(" Sprint Mart / Morris Petro "),
    ODYSII("Odysii");

    //Getter method
    public String getRetailerName() {
        return retailerName;
    }
    //Property
    private String retailerName;
    //Constractor
    RetailerName(String retailerName) {
        this.retailerName = retailerName;

    }
}
