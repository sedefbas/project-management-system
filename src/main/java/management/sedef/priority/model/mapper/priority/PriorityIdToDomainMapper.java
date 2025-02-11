package management.sedef.priority.model.mapper.priority;

import management.sedef.priority.model.Priority;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriorityIdToDomainMapper {
    Priority toDomain(Long id);
}
