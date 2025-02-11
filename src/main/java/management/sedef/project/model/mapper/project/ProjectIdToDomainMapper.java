package management.sedef.project.model.mapper.project;

import management.sedef.project.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectIdToDomainMapper {
    Project toDomain(Long id);
}
