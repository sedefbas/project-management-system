package management.sedef.project.model.claims;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import management.sedef.auth.model.enums.RoleName;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectUserClaims {

    private Long userId;

    private Long groupId;

    private Long subGroupId;

    private RoleName role;

    private Long projectId;

    private Long companyId;

    public ProjectUserClaims(Long userId, Long groupId, RoleName role, Long projectId, Long companyId) {
        this.userId = userId;
        this.groupId = groupId;
        this.role = role;
        this.projectId=projectId;
        this.companyId = companyId;
    }

}