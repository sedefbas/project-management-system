package management.sedef.project.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.project.model.Project;
import management.sedef.project.model.entity.ProjectEntity;
import management.sedef.project.model.mapper.ProjectEntityToDomainMapper;
import management.sedef.project.model.mapper.ProjectToEntityMapper;
import management.sedef.project.port.ProjectReadAdapter;
import management.sedef.project.port.ProjectSaveAdapter;
import management.sedef.project.repository.ProjectRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectAdapter implements ProjectSaveAdapter, ProjectReadAdapter {

    private final ProjectRepository repository;
    private final ProjectToEntityMapper projectToEntityMapper = ProjectToEntityMapper.initialize();
    private final ProjectEntityToDomainMapper projectEntityToDomainMapper = ProjectEntityToDomainMapper.initialize();

    @Override
    public Project save(Project project) {
        ProjectEntity projectEntity = projectToEntityMapper.map(project);
        repository.save(projectEntity);
        return projectEntityToDomainMapper.map(projectEntity);
    }

    @Override
    public int countProjectsByCompanyId(Long companyId) {
        return repository.countProjectsByCompanyId(companyId);
    }
}
