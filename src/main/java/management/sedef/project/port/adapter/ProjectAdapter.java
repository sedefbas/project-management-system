package management.sedef.project.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.project.exception.ProjectNotFoundException;
import management.sedef.project.model.Project;
import management.sedef.project.model.entity.ProjectEntity;
import management.sedef.project.model.mapper.project.ProjectEntityToDomainMapper;
import management.sedef.project.model.mapper.project.ProjectToEntityMapper;
import management.sedef.project.port.projectPort.ProjectDeleteAdapter;
import management.sedef.project.port.projectPort.ProjectReadAdapter;
import management.sedef.project.port.projectPort.ProjectSaveAdapter;
import management.sedef.project.repository.ProjectRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectAdapter implements ProjectSaveAdapter, ProjectReadAdapter, ProjectDeleteAdapter {

    private final ProjectRepository repository;
    private final ProjectToEntityMapper projectToEntityMapper = ProjectToEntityMapper.initialize();
    private final ProjectEntityToDomainMapper projectEntityToDomainMapper ;


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

    @Override
    public Project findByIdAndCompanyId(Long projectId, Long companyId) {
        ProjectEntity projectEntity = repository.findByIdAndCompanyId(projectId,companyId).
                orElseThrow(() -> new ProjectNotFoundException("Şirket ID: " + companyId + " ile proje ID: " + projectId + " bulunamadı."));
        return projectEntityToDomainMapper.map(projectEntity);
    }

    @Override
    public List<Project> getAllProjects(Long companyId) {
        List<ProjectEntity> projectEntities = repository.findAllByCompanyId(companyId);
        return projectEntities.stream()
                .map(projectEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Project findById(Long projectId) {
        ProjectEntity projectEntity = repository.findById(projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Proje ID: " + projectId + " bulunamadı."));
        return projectEntityToDomainMapper.map(projectEntity);
    }

    @Override
    public void delete(Project project) {
        final ProjectEntity projectEntity = projectToEntityMapper.map(project);
       repository.delete(projectEntity);
    }
}
