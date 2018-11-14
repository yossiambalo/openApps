package com.odysii.selenium.page.openApps.dev.summary;

public enum  ApplicationStatus {
    SUBMITTED("Submitted"),CERTIFIED("Certified"), LIVE("Live"),REJECT("Rejected"),PRESUBMITTED("Presubmitted");

    public String getStatus() {
        return status;
    }

    private String status;
    ApplicationStatus(String status) {
        this.status = status;
    }
}
