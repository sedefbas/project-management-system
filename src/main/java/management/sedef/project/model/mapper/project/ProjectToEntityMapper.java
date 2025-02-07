package management.sedef.project.model.mapper.project;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.project.model.Project;
import management.sedef.project.model.entity.ProjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectToEntityMapper extends BaseMapper<Project, ProjectEntity> {
    static ProjectToEntityMapper initialize(){
        return Mappers.getMapper(ProjectToEntityMapper.class);
    }

}
