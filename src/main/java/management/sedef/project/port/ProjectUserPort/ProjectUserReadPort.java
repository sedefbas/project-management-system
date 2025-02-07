package management.sedef.project.port.ProjectUserPort;

import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.entity.ProjectUserEntity;

import java.util.List;

public interface ProjectUserReadPort {
    ProjectUser findByUserIdAndProjectId(Long userId, Long projectId);
    List<ProjectUser> findByProjectId(Long projectId);
    List<ProjectUser> findByUserId(Long userId);

}
