package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.entity.GroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupEntityToDomainMapper extends BaseMapper<GroupEntity, Group> {
    static GroupEntityToDomainMapper initialize(){
        return Mappers.getMapper(GroupEntityToDomainMapper.class);
}
}
