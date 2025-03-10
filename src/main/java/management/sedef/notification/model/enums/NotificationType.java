package management.sedef.notification.model.enums;

public enum NotificationType {
    IN_APP("In-App"),
    EMAIL("Email"),
    SMS("SMS"),
    PUSH_NOTIFICATION("Push NotificationDocument");

    private final String value;

    NotificationType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
