package management.sedef.project.port.projectPort;

import management.sedef.company.model.Group;
import management.sedef.project.model.Project;

import java.util.List;

public interface ProjectReadAdapter {
    int countProjectsByCompanyId(Long companyId);
    Project findByIdAndCompanyId(Long projectId,Long companyId);
    List<Project> getAllProjects(Long companyId);
    List<Group> findGroupsByProjectIdAndCompanyId(Long projectId, Long companyId);
    Project findById(Long projectId);
}
