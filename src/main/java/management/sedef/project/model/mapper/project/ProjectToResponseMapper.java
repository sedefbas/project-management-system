package management.sedef.project.model.mapper.project;
import management.sedef.project.model.Project;
import management.sedef.project.model.response.ProjectResponse;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;



@Component
@Mapper(componentModel = "spring")
public abstract class ProjectToResponseMapper {
    public abstract ProjectResponse map(Project project);
}
