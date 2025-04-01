package management.sedef.project.model.mapper.project;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.mapper.groupmapper.GroupEntityToDomainMapper;
import management.sedef.project.model.Project;
import management.sedef.project.model.entity.ProjectEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {GroupEntityToDomainMapper.class})
public interface ProjectEntityToDomainMapper extends BaseMapper<ProjectEntity, Project> {

    @Override
    @Mapping(target = "groups", ignore = true)  // Döngüyü kırmak için grupları şimdilik ignore ediyoruz
    Project map(ProjectEntity entity);

    @AfterMapping
    default void afterMapping(@MappingTarget Project project, ProjectEntity entity,
                              @Context GroupEntityToDomainMapper groupMapper) {
        if (entity.getGroups() != null) {
            List<Group> mappedGroups = entity.getGroups().stream()
                    .map(groupMapper::map)
                    .collect(Collectors.toList());
            project.setGroups(mappedGroups);
        }
    }
}
