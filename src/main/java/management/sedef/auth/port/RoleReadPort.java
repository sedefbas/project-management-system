package management.sedef.auth.port;

import management.sedef.auth.model.Role;
import management.sedef.auth.model.enums.RoleName;

import java.util.Optional;

public interface RoleReadPort {
    Optional<Role> findByName(RoleName name);
}
