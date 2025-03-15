package management.sedef.notification.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.issue.kafka.event.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;

import java.time.Instant;

@Getter
@Setter
@Builder
public class Notification {

    private String id;
    private String message;
    private NotificationType type;
    private EventType eventType;
    private Long senderId;
    private Long recipientId;
    private boolean isGeneral;
    private Instant createdAt;
    private NotificationStatus notificationStatus;
    private Long projectId;
    private Long issueId;
}
