package edu.neu.webtools.AptService.model;

public enum ComplaintType {
	PLUMBING("Plumbing"),
    ELECTRICITY("Electricity");
	
	private final String displayName;

	ComplaintType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}