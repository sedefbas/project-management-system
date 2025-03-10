package management.sedef.notification.model.document;

import jakarta.persistence.Id;
import lombok.*;
import management.sedef.notification.model.enums.EventType;
import management.sedef.notification.model.enums.NotificationStatus;
import management.sedef.notification.model.enums.NotificationType;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Map;

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
    private boolean isGeneral;
    private Instant createdAt;
    private Map<Long, NotificationStatus> recipientIds;
    private Long projectId;
    private Long issueId;
}
