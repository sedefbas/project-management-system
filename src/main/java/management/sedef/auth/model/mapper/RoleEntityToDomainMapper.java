package management.sedef.auth.model.mapper;

import management.sedef.auth.model.Role;
import management.sedef.auth.model.entity.RoleEntity;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface RoleEntityToDomainMapper extends BaseMapper<RoleEntity, Role> {

    static RoleEntityToDomainMapper initialize() {
        return Mappers.getMapper(RoleEntityToDomainMapper.class);
    }

}
