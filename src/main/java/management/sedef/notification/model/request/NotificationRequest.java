package management.sedef.notification.model.request;

import lombok.*;
import management.sedef.notification.model.enums.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;


import java.util.Map;

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
    private boolean isGeneral;
    private Map<Long, NotificationStatus> recipientIds;
    private Long projectId;
    private Long issueId;
}
