package management.sedef.priority.model.mapper.priority;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.entity.PriorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PriorityEntityToDomainMapper extends BaseMapper<PriorityEntity, Priority> {
    static PriorityEntityToDomainMapper initialize(){
        return Mappers.getMapper(PriorityEntityToDomainMapper.class);
    }

}
