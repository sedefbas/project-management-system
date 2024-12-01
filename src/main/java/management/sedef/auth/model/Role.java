package management.sedef.auth.model;

import lombok.Getter;
import lombok.Setter;
import management.sedef.auth.model.entity.PermissionEntity;
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

}
