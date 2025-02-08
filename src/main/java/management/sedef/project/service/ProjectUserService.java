package management.sedef.project.service;

import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.request.ProjectUserRequest;
import management.sedef.project.model.request.ProjectUserUpdateRequest;

import java.util.List;

public interface ProjectUserService {

    void sendUserInvitationForProject(ProjectUserRequest request, Long projectId, Long companyId, String email);
    void addUserToProjectByToken(String token);
    void removeUserFromProjectById(Long userId, Long projectId);
    void updateUserInProject(ProjectUserUpdateRequest request, Long userId, Long projectId, Long companyId);
    List<ProjectUser> getUsersForProject(Long projectId );
    List<ProjectUser> getProjectByToken(String token);
    List<ProjectUser> getUsersBySubGroupId(Long subgroup);
    List<ProjectUser> getUsersBygroupId(Long groupId);
}
