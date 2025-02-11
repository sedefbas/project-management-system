package management.sedef.project.model.mapper.project;

import management.sedef.project.model.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectToIdMapper {
    default Long toId(Project project) {
        return (project != null) ? project.getId() : null;
    }
}
