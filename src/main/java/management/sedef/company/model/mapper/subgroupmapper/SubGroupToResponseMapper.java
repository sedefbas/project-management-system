package management.sedef.company.model.mapper.subgroupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.SubGroup;
import management.sedef.company.model.response.SubGroupResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubGroupToResponseMapper extends BaseMapper<SubGroup, SubGroupResponse> {

    static SubGroupToResponseMapper initialize(){
        return Mappers.getMapper(SubGroupToResponseMapper.class);
    }

}
