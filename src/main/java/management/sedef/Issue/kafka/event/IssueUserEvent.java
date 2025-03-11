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

    private Long issueId;
    private Long assignedUserId;
    private Date assignmentDate;
    private Long assignedById;
    private Role role;
}
