package management.sedef.notification.model;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.notification.model.enums.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;

import java.time.Instant;
import java.util.Map;

@Getter
@Setter
@Builder
public class Notification {

    private String id;
    private String message;
    private NotificationType type;
    private EventType eventType;
    private Long senderId;
    private boolean isGeneral;
    private Instant createdAt;
    private Map<Long, NotificationStatus> recipientIds;
    private Long projectId;
    private Long issueId;
}
