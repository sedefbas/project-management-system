package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.entity.GroupEntity;
import management.sedef.project.model.mapper.project.ProjectEntityToDomainMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {ProjectEntityToDomainMapper.class})
public abstract class GroupEntityToDomainMapper {

    @Mapping(target = "project", ignore = true) // İlk dönüşümde projeleri eklemiyoruz
    public abstract Group map(GroupEntity entity);

    @AfterMapping
    protected void setProjects(@MappingTarget Group group, GroupEntity entity, ProjectEntityToDomainMapper projectMapper) {
        if (entity.getProject() != null) {
            group.setProject(projectMapper.map(entity.getProject()));
        }
    }
}
