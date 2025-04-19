package management.sedef.project.model.mapper.project;

import management.sedef.common.model.entity.mapper.BaseMapper;

import management.sedef.project.model.Project;
import management.sedef.project.model.entity.ProjectEntity;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface ProjectEntityToDomainMapper extends BaseMapper<ProjectEntity, Project> {

    Project map(ProjectEntity entity);

}
