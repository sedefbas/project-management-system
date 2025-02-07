package management.sedef.project.model.mapper.project;

import management.sedef.company.model.Group;
import management.sedef.company.service.GroupService;
import management.sedef.project.model.Project;
import management.sedef.project.model.enums.ProjectStatus;
import management.sedef.project.model.request.ProjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ProjectRequestToDomainMapper {

    @Autowired
    protected GroupService groupService;

    public Project map(ProjectRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("ProjectRequest cannot be null");
        }

        List<Group> groups = mapGroups(request.getGroupIds());

        return Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .photo(request.getPhoto())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .projectStatus(request.getStatus() != null ? request.getStatus() : ProjectStatus.NOT_STARTED) // Varsayılan durum
                .groups(groups)
                .build();
    }

    protected List<Group> mapGroups(List<Long> groupIds) {
        return groupIds.stream()
                .map(groupService::findById)
                .collect(Collectors.toList());
    }
}

