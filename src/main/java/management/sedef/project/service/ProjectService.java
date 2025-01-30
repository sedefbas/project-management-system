package management.sedef.project.service;

import management.sedef.company.model.Group;
import management.sedef.project.model.Project;
import management.sedef.project.model.request.ProjectRequest;
import management.sedef.project.model.request.ProjectUpdateRequest;
import java.util.List;



public interface ProjectService {

    void create(ProjectRequest request, Long companyId);

    void delete(Long projectId, Long companyId);

    void update(Long projectId, Long companyId, ProjectUpdateRequest request);

    Project findByIdAndCompanyId(Long projectId, Long companyId);

    List<Project> getAllProjectsByCompanyId(Long companyId);

    List<Group> findGroupsByProjectIdAndCompanyId(Long projectId, Long companyId);

    void removeGroupFromProject(Long companyId, Long projectId, Long groupId);

    void addGroupToProject(Long companyId, Long projectId, Long groupId);
}
