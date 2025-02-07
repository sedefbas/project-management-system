package management.sedef.project.model.mapper.projectUser;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.entity.ProjectUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ProjectUserToEntityMapper extends BaseMapper<ProjectUser, ProjectUserEntity> {
    static ProjectUserToEntityMapper initialize(){
        return Mappers.getMapper(ProjectUserToEntityMapper.class);
    }
}
