package management.sedef.priority.model.mapper.priority;

import management.sedef.priority.model.Priority;
import management.sedef.priority.model.response.PrioritySummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PriorityToPrioritySummaryResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    PrioritySummaryResponse toPrioritySummaryResponse(Priority priority);
}

