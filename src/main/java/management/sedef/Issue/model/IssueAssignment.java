package management.sedef.issue.model;
import lombok.*;
import management.sedef.auth.model.Role;
import management.sedef.user.model.User;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueAssignment {

    private Long id;
    private Issue issue;
    private User assignedUser;
    private Date assignmentDate;
    private User assignedBy;
    private Role role;

    public String getUserfullName() {
        return assignedUser.getFirstName() + assignedUser.getLastName();
    }

    public String getAssignedByfullName() {
        return assignedBy.getFirstName() + assignedBy.getLastName();
    }

}
