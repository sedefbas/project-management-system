package management.sedef.issue.kafka.event;

public enum EventType {
    ISSUE_CREATED("Issue Created"),
    ISSUE_PRIORITY_UPDATED("Issue Priority Updated"),
    ISSUE_LABEL_UPDATED("Issue Label Updated"),
    ISSUE_STAGE_UPDATED("Issue Stage Updated"),
    ISSUE_ASSIGNED("Issue Assigned"),
    ISSUE_ASSIGNED_TO_USER("Issue Assigned to User"),
    ISSUE_UNASSIGNED_FROM_USER("Issue Unassigned from User"),
    ISSUE_ROLE_UPDATED("Issue Role Updated"),
    ISSUE_COMMENT_CREATED("Issue Comment Created"),
    ISSUE_COMMENT_UPDATED("Issue Comment Updated"),
    ISSUE_COMMENT_DELETED("Issue Comment Deleted"),
    ISSUE_CLOSED("Issue Closed"),
    ISSUE_REOPENED("Issue Reopened"),

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