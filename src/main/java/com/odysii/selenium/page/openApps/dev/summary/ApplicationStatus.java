package com.odysii.selenium.page.openApps.dev.summary;

public enum  ApplicationStatus {
    SUBMITTED("Submitted"),CERTIFIED("Certified"),LLIVE("Live");

    public String getStatus() {
        return status;
    }

    private String status;
    ApplicationStatus(String status) {
        this.status = status;
    }
}
