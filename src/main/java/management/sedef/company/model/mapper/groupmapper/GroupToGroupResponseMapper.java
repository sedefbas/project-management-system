package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.response.GroupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupToGroupResponseMapper extends BaseMapper<Group, GroupResponse> {
    static GroupToGroupResponseMapper initialize(){
        return Mappers.getMapper(GroupToGroupResponseMapper.class);
    }
}
