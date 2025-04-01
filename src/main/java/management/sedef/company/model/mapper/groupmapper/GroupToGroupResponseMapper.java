package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.response.GroupResponse;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface GroupToGroupResponseMapper extends BaseMapper<Group, GroupResponse> {

    GroupResponse map(Group group);
}