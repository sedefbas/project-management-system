package management.sedef.issue.kafka.event;


import lombok.*;
import management.sedef.auth.model.Role;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueUserEvent {

    private Long issueAssignmentId;
    private Long issueId;
    private Long assignedUserId; //atanan kullanıcı
    private Date assignmentDate;
    private Long assignedById; // Atamayı yapan kullanıcı
    private Role role;
    private EventType type;
}
