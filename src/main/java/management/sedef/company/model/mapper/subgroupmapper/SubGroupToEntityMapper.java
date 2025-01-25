package management.sedef.company.model.mapper.subgroupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.SubGroup;
import management.sedef.company.model.entity.SubGroupEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubGroupToEntityMapper extends BaseMapper<SubGroup, SubGroupEntity> {
    static SubGroupToEntityMapper initialize(){
        return Mappers.getMapper(SubGroupToEntityMapper.class);
    }
}
