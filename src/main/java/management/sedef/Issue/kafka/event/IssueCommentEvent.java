package management.sedef.issue.kafka.event;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueCommentEvent {
    private Long issueId;
    private EventType eventType;
    private Instant modifiedAt;
    private Long commenterId;
}
