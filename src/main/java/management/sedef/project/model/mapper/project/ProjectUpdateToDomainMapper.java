package management.sedef.project.model.mapper.project;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.project.model.Project;
import management.sedef.project.model.request.ProjectUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectUpdateToDomainMapper  extends BaseMapper<ProjectUpdateRequest, Project> {

    static ProjectUpdateToDomainMapper initialize(){
        return Mappers.getMapper(ProjectUpdateToDomainMapper.class);
    }

}
