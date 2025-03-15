package management.sedef.notification.model.request;

import lombok.*;
import management.sedef.issue.kafka.event.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String message;
    private NotificationType type;
    private EventType eventType;
    private Long senderId;
    private Long recipientId;
    private boolean isGeneral;
    private NotificationStatus notificationStatus;
    private Long projectId;
    private Long issueId;
}
