package management.sedef.company.model.mapper.subgroupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.SubGroup;
import management.sedef.company.model.entity.SubGroupEntity;
import management.sedef.company.model.mapper.groupmapper.GroupToGroupResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubGroupEntityToDomainMapper extends BaseMapper<SubGroupEntity, SubGroup> {
    static SubGroupEntityToDomainMapper initialize(){
        return Mappers.getMapper(SubGroupEntityToDomainMapper.class);
    }

}
