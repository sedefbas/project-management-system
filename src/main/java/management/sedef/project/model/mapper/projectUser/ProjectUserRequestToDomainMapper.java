package management.sedef.project.model.mapper.projectUser;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Role;
import management.sedef.company.model.Group;
import management.sedef.company.model.SubGroup;
import management.sedef.company.service.GroupService;
import management.sedef.company.service.SubGroupService;
import management.sedef.project.model.Project;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.request.ProjectUserUpdateRequest;
import management.sedef.project.service.ProjectService;
import management.sedef.user.model.User;
import management.sedef.user.port.adapter.UserAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectUserRequestToDomainMapper {

    private final GroupService groupService;
    private final SubGroupService subGroupService;
    private final UserAdapter userAdapter;
    private final ProjectService projectService;

    public ProjectUser map(ProjectUserUpdateRequest request, Long userId , Long projectId, Long companyId) {

        Project project = projectService.findByIdAndCompanyId(projectId, companyId);

        User user = userAdapter.findById(userId).get();

        Group group = groupService.findById(request.getGroupId());
        SubGroup subGroup = null;
        if (request.getSubGroupId() != null) {
            subGroup = subGroupService.findById(request.getSubGroupId());
        }

        Role role = Role.fromRoleName(request.getRole().name());

        return ProjectUser.builder()
                .user(user)
                .project(project)
                .group(group)
                .subGroup(subGroup)
                .role(role)
                .build();
    }
}