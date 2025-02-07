package management.sedef.project.model.mapper.projectUser;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.Role;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.company.model.Company;
import management.sedef.company.model.Group;
import management.sedef.company.model.SubGroup;
import management.sedef.company.service.CompanyService;
import management.sedef.company.service.GroupService;
import management.sedef.company.service.SubGroupService;
import management.sedef.project.model.Project;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.claims.ProjectUserClaims;
import management.sedef.project.service.ProjectService;
import management.sedef.user.model.User;
import management.sedef.user.port.adapter.UserAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectUserClaimsToDomainMapper {

    private final GroupService groupService;
    private final SubGroupService subGroupService;
    private final UserAdapter userAdapter;
    private final ProjectService projectService;
    private final CompanyService companyService;

    public ProjectUser map(ProjectUserClaims claims) {

        Group group = groupService.findById(claims.getGroupId());


        SubGroup subGroup = null;
        if (claims.getSubGroupId() != null) {
            subGroup = subGroupService.findById(claims.getSubGroupId());
        }

        User user = userAdapter.findById(claims.getUserId()).get();
        Project project = projectService.findByIdAndCompanyId(claims.getProjectId(), claims.getCompanyId());

        Role role = Role.fromRoleName(String.valueOf(claims.getRole()));

        return ProjectUser.builder()
                .user(user)
                .project(project)
                .group(group)
                .subGroup(subGroup)
                .role(role)
                .build();
    }

}
