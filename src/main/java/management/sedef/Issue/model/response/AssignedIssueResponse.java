package management.sedef.issue.model.response;

import java.sql.Date;

import lombok.Builder;
import lombok.Data;
import management.sedef.auth.model.enums.RoleName;

@Data
@Builder
public class AssignedIssueResponse {
    private Long issueId;
    private String issueName;
    private String explanation;
    private Date startDate;
    private Date deadLineDate;
    private RoleName assignmentRole;
    private String stageName;
    private String priorityName;
    private String labelName;
}