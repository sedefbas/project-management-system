package management.sedef.priority.model.mapper.priority;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.entity.PriorityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriorityToEntityMapper extends BaseMapper<Priority, PriorityEntity> {
    static PriorityToEntityMapper initialize(){
        return Mappers.getMapper(PriorityToEntityMapper.class);
    }

}
