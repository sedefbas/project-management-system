package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.response.GroupProjectResponse;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface GroupToGroupProjectResponseMapper extends BaseMapper<Group, GroupProjectResponse> {

    @Override
    GroupProjectResponse map(Group group);

}
