package management.sedef.auth.model;

import lombok.Getter;
import lombok.Setter;
import management.sedef.auth.model.entity.PermissionEntity;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.auth.model.enums.RoleStatus;

import java.util.List;

@Getter
@Setter
public class Role {

    private Long id;
    private String name;
    private String description;
    private RoleStatus status;
    private List<PermissionEntity> permissions;


    public List<String> getPermissionNames() {
        return permissions.stream()
                .map(PermissionEntity::getName)
                .toList();
    }

    //şimdilik admin için eklemem gerekti.d
    public Boolean validateMemberOrCompanyOwnerRole() {
        return this.name.equals(RoleName.MEMBER.name())
                || this.name.equals(RoleName.COMPANY_OWNER.name())
                || this.name.equals(RoleName.ADMIN.name());
    }



}
