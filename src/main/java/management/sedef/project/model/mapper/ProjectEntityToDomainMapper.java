package management.sedef.project.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.project.model.Project;
import management.sedef.project.model.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectEntityToDomainMapper extends BaseMapper<ProjectEntity, Project>{

    static ProjectEntityToDomainMapper initialize(){
        return Mappers.getMapper(ProjectEntityToDomainMapper.class);
    }
}
