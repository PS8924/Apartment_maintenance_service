package edu.neu.webtools.AptService.model;

public enum RoleType {
    OWNER("Owner"),
    TENANT("Tenant"),
	STAFF("Staff");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}