package management.sedef.issue.model.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.auth.model.enums.RoleName;

@Getter
@Setter
@Builder
public class IssueAssignmentRequest {
    private Long issueId;
    private Long assignedUserId;
    private RoleName role;
}
