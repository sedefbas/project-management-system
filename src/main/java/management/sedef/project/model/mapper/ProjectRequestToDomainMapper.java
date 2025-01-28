package management.sedef.project.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.service.GroupService;
import management.sedef.project.model.Project;
import management.sedef.project.model.request.ProjectRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public abstract class ProjectRequestToDomainMapper implements BaseMapper<ProjectRequest, Project> {

    @Autowired
    protected GroupService groupService;

    @Mapping(target = "groups", expression = "java(mapGroups(request.getGroupIds()))")
    public abstract Project map(ProjectRequest request);


    protected List<Group> mapGroups(List<Long> groupIds) {
        if (groupIds == null || groupIds.isEmpty()) {
            return Collections.emptyList();
        }
        return groupIds.stream()
                .map(groupService::findById)
                .collect(Collectors.toList());
    }
}

