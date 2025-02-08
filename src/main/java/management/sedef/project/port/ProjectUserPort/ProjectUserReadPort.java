package management.sedef.project.port.ProjectUserPort;

import management.sedef.project.model.ProjectUser;

import java.util.List;



public interface ProjectUserReadPort {
    ProjectUser findByUserIdAndProjectId(Long userId, Long projectId);
    List<ProjectUser> findByProjectId(Long projectId);
    List<ProjectUser> findByUserId(Long userId);
    boolean existsByUserIdAndProjectId(Long userId,Long projectId);
    List<ProjectUser> findUsersBySubGroupId(Long subGroupId);
    List<ProjectUser> findUsersByGroupId(Long groupId);
}
