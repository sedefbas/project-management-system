package management.sedef.project.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.company.exception.GroupNotFoundException;
import management.sedef.company.model.Company;
import management.sedef.company.model.Group;
import management.sedef.company.service.CompanyService;
import management.sedef.company.service.GroupService;
import management.sedef.project.model.Project;
import management.sedef.project.model.mapper.project.ProjectRequestToDomainMapper;
import management.sedef.project.model.mapper.project.ProjectUpdateToDomainMapper;
import management.sedef.project.model.request.ProjectRequest;
import management.sedef.project.model.request.ProjectUpdateRequest;
import management.sedef.project.port.projectPort.ProjectDeleteAdapter;
import management.sedef.project.port.projectPort.ProjectReadAdapter;
import management.sedef.project.port.projectPort.ProjectSaveAdapter;
import management.sedef.project.service.ProjectService;
import management.sedef.project.validation.ProjectValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectSaveAdapter saveAdapter;
    private final ProjectReadAdapter readAdapter;
    private final ProjectDeleteAdapter deleteAdapter;
    private final CompanyService companyService;
    private final ProjectRequestToDomainMapper projectRequestToDomainMapper;
    private final ProjectUpdateToDomainMapper projectUpdateToDomainMapper = ProjectUpdateToDomainMapper.initialize();
    private final GroupService groupService;




    @Override
    public void create(ProjectRequest request, Long companyId) {
        Company company = companyService.findCompanyById(companyId);

        int existingProjectCount = readAdapter.countProjectsByCompanyId(companyId);
        ProjectValidator.validateMaxProjects(company.getSubscriptionPlan(), existingProjectCount);
        Project project = projectRequestToDomainMapper.map(request);
        project.setCompany(company);
        saveAdapter.save(project);
    }



    @Override
    public void delete(Long projectId, Long companyId) {
        Project project = readAdapter.findByIdAndCompanyId(projectId, companyId);
        deleteAdapter.delete(project);
    }

    @Override
    public void update(Long projectId, Long companyId, ProjectUpdateRequest request) {
        Project project = readAdapter.findByIdAndCompanyId(projectId, companyId);
        Project updatedProject = projectUpdateToDomainMapper.map(request);
        updatedProject.setId(project.getId());
        updatedProject.setCompany(project.getCompany()); // Şirket ID'si korunur
        saveAdapter.save(updatedProject);
    }

    @Override
    public Project findByIdAndCompanyId(Long projectId, Long companyId) {
        return readAdapter.findByIdAndCompanyId(projectId,companyId);
    }

    @Override
    public List<Project> getAllProjectsByCompanyId(Long companyId) {

        return readAdapter.getAllProjects(companyId);
    }

//    @Override
//    public void removeGroupFromProject(Long companyId, Long projectId, Long groupId) {
//
//        Project project = findByIdAndCompanyId(projectId, companyId);
//        List<Group> currentGroups = findGroupsByProjectIdAndCompanyId(projectId, companyId);
//
//        boolean existsInProject = currentGroups.stream()
//                .anyMatch(g -> g.getId().equals(groupId));
//
//        if (!existsInProject) {
//            throw new GroupNotFoundException("Grup, projede mevcut değil.");
//        }
//
//        saveAdapter.save(project);
//    }
//
//
//    @Override
//    public void addGroupToProject(Long companyId, Long projectId, Long groupId) {
//
//        Project project = findByIdAndCompanyId(projectId, companyId);
//
//        List<Group> currentGroups = findGroupsByProjectIdAndCompanyId(projectId,companyId);
//
//        List<Long> currentGroupIds = currentGroups.stream()
//                .map(Group::getId)
//                .collect(Collectors.toList());
//
//        if (!currentGroupIds.contains(groupId)) {
//            Group groupToAdd = groupService.findById(groupId);
//            saveAdapter.save(project);
//        }
//    }
//




}
