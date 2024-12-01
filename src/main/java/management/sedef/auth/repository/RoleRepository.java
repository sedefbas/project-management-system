package management.sedef.auth.repository;


import management.sedef.auth.model.entity.RoleEntity;
import management.sedef.auth.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    Optional<RoleEntity> findByName(RoleName name);

}

