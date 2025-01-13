package management.sedef.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Role;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.auth.model.mapper.RoleEntityToDomainMapper;
import management.sedef.auth.port.RoleReadPort;
import management.sedef.auth.repository.RoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
class RoleAdapter implements RoleReadPort {

    private final RoleRepository roleRepository;


    private final RoleEntityToDomainMapper roleEntityToDomainMapper = RoleEntityToDomainMapper.initialize();


    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name)
                .map(roleEntityToDomainMapper::map);
    }

}
