package management.sedef.issue.kafka.event;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueMailEvent {
    private Long issueAssignmentId;
}
