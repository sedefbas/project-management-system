package management.sedef.issue.model.dto;

import java.util.List;
import lombok.Data;
import management.sedef.auth.model.Role;

@Data
public class IssueAssignmentGroupDTO {
    private Role assignmentType;
    private List<AssignedUserDTO> users;
}
