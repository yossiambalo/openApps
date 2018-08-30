package com.odysii.selenium.page.openApps.dev.summary;

public enum  ApplicationStatus {
    SUBMITTED("Submitted"),CERTIFIED("Certified"), LIVE("Live"),REJECT("Rejected");

    public String getStatus() {
        return status;
    }

    private String status;
    ApplicationStatus(String status) {
        this.status = status;
    }
}
