package management.sedef.priority.model.mapper.priority;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.request.PriorityRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriorityRequestToDomainMapper extends BaseMapper<PriorityRequest, Priority> {
    static PriorityRequestToDomainMapper initialize(){
        return Mappers.getMapper(PriorityRequestToDomainMapper.class);
    }
}
