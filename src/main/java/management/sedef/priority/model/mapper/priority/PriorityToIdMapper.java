package management.sedef.priority.model.mapper.priority;

import management.sedef.priority.model.Priority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityToIdMapper {
    default Long toId(Priority priority) {
        return (priority != null) ? priority.getId() : null;
    }
}
