package management.sedef.issue.model.dto;

import lombok.Builder;
import lombok.Data;
import management.sedef.auth.model.enums.RoleName;

@Data
@Builder
public class AssignedUserDTO {
    private Long userId;
    private String firstName;
    private String lastName;
    private RoleName roleName;
}
