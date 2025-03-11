package management.sedef.notification.model.enums;

public enum EventType {
    ISSUE_CREATED("Issue Created"),
    ISSUE_UPDATED("Issue Updated"),
    ISSUE_ASSIGNED("Issue Assigned"),
    ISSUE_ASSIGNED_TO_USER("Issue Assigned to User"),
    ISSUE_COMMENTED("Issue Commented"),

    PROJECT_CREATED("Project Created"),
    PROJECT_UPDATED("Project Updated"),
    PROJECT_DELETED("Project Deleted"),
    PROJECT_ASSIGNED_TO_USER("Project Assigned to User"),

    USER_INVITED("User Invited"),
    USER_REMOVED("User Removed"),

    GENERAL_ANNOUNCEMENT("General Announcement");

    private final String value;

    EventType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
