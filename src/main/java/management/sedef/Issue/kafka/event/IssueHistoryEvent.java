package management.sedef.issue.kafka.event;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueHistoryEvent {
        private Long issueId;
        private EventType eventType;
        private String oldValues;
        private String newValues;
        private Long userId; //kim tarafından değiştirildiği.
        private Instant modifiedAt;
        private Long AssignedUserId;
}
