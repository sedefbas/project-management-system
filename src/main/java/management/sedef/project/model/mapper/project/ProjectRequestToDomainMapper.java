package management.sedef.project.model.mapper.project;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.project.model.Project;
import management.sedef.project.model.request.ProjectRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectRequestToDomainMapper extends BaseMapper<ProjectRequest, Project> {

    Project map(ProjectRequest entity);

}
