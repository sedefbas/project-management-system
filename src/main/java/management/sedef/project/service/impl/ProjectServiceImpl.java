package management.sedef.project.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Company;
import management.sedef.company.service.CompanyService;
import management.sedef.project.model.Project;
import management.sedef.project.model.mapper.ProjectRequestToDomainMapper;
import management.sedef.project.model.request.ProjectRequest;
import management.sedef.project.port.ProjectReadAdapter;
import management.sedef.project.port.ProjectSaveAdapter;
import management.sedef.project.service.ProjectService;
import management.sedef.project.validation.ProjectValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectSaveAdapter saveAdapter;
    private final ProjectReadAdapter readAdapter;
    private final CompanyService companyService;
    private final ProjectRequestToDomainMapper projectRequestToDomainMapper ;

    @Override
    public void create(ProjectRequest request, Long companyId) {
        Company company = companyService.findCompanyById(companyId);
        int existingProjectCount = readAdapter.countProjectsByCompanyId(companyId);
        ProjectValidator.validateMaxProjects(company.getSubscriptionPlan(), existingProjectCount);
        Project project = projectRequestToDomainMapper.map(request);
        project.setCompany(company);
        saveAdapter.save(project);
    }



}
