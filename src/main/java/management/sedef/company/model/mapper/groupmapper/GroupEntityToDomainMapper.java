package management.sedef.company.model.mapper.groupmapper;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.entity.GroupEntity;


import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface GroupEntityToDomainMapper extends BaseMapper<GroupEntity,Group> {

     Group map(GroupEntity entity);

}
