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


    public static Role fromRoleName(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        // Eğer roleName üzerinden daha fazla işleme gerek varsa (description vs.) burada ekleyebilirsiniz.
        return role;
    }


    public List<String> getPermissionNames() {
        return permissions.stream()
                .map(PermissionEntity::getName)
                .toList();
    }

    //todo şimdilik admin için eklemem gerekti.d
    public Boolean validateMemberOrCompanyOwnerRole() {
        return this.name.equals(RoleName.USER.name())
                || this.name.equals(RoleName.COMPANY_OWNER.name())
                || this.name.equals(RoleName.ADMIN.name());
    }



}
