package com.odysii.selenium.page.openApps.helper.appDetails;

public enum  AvailabilityType {
    PRIVATE("Private"),PUBLIC("Public");

    private String availability;

    public String getAvailability() {
        return availability;
    }

    AvailabilityType(String availability) {
        this.availability = availability;
    }

}
