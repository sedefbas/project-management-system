package management.sedef.notification.model.document;

import jakarta.persistence.Id;
import lombok.*;
import management.sedef.issue.kafka.event.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDocument {

    @Id
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
