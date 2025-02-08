package management.sedef.project.port.adapter;


import lombok.RequiredArgsConstructor;
import management.sedef.project.exception.ProjectNotFoundException;
import management.sedef.project.exception.ProjectUserNotFoundException;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.entity.ProjectUserEntity;
import management.sedef.project.model.mapper.projectUser.ProjectUserEntityToDomainMapper;
import management.sedef.project.model.mapper.projectUser.ProjectUserToEntityMapper;
import management.sedef.project.port.ProjectUserPort.ProjectUserDeletePort;
import management.sedef.project.port.ProjectUserPort.ProjectUserReadPort;
import management.sedef.project.port.ProjectUserPort.ProjectUserSavePort;
import management.sedef.project.repository.ProjectUserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectUserAdapter implements ProjectUserSavePort, ProjectUserDeletePort, ProjectUserReadPort {

    private final ProjectUserRepository projectUserRepository;
    private final ProjectUserEntityToDomainMapper projectUserEntityToDomainMapper = ProjectUserEntityToDomainMapper.initialize();
    private final ProjectUserToEntityMapper projectUserToEntityMapper = ProjectUserToEntityMapper.initialize();

    @Override
    public ProjectUser save(ProjectUser projectUser) {
        ProjectUserEntity projectUserEntity = projectUserToEntityMapper.map(projectUser);
        ProjectUserEntity savedEntity = projectUserRepository.save(projectUserEntity);
        return projectUserEntityToDomainMapper.map(savedEntity);
    }

    @Override
    public void delete(ProjectUser projectUser) {
        final ProjectUserEntity projectUserEntity = projectUserToEntityMapper.map(projectUser);
        projectUserRepository.delete(projectUserEntity);
    }


    @Override
    public ProjectUser findByUserIdAndProjectId(Long userId, Long projectId) {

        ProjectUserEntity projectUserEntity = projectUserRepository.findByUserIdAndProjectId(userId, projectId)
                .orElseThrow(() -> new ProjectNotFoundException("Projeye ait böyle bir user bulunamadı."));

        return projectUserEntityToDomainMapper.map(projectUserEntity);
    }


    @Override
    public List<ProjectUser> findByProjectId(Long projectId) {
        List<ProjectUserEntity> projectUserEntities = projectUserRepository.findByProjectId(projectId);

        return projectUserEntities.stream()
                .map(projectUserEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }


    @Override
    public List<ProjectUser> findByUserId(Long userId) {
        List<ProjectUserEntity> projectUserEntities = projectUserRepository.findByUserId(userId);

        return projectUserEntities.stream()
                .map(projectUserEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    public boolean existsByUserIdAndProjectId(Long userId, Long projectId) {
        return projectUserRepository.existsByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<ProjectUser> findUsersBySubGroupId(Long subGroupId) {
        List<ProjectUserEntity> projectUserEntities = projectUserRepository.findBySubGroupId(subGroupId)
                .orElseThrow(() -> new ProjectUserNotFoundException("Belirtilen subGroupId: " + subGroupId + " için herhangi bir ProjectUser bulunamadı."));

        return projectUserEntities.stream()
                .map(projectUserEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectUser> findUsersByGroupId(Long groupId) {
        List<ProjectUserEntity> projectUserEntities = projectUserRepository.findBySubGroupId(groupId)
                .orElseThrow(()-> new ProjectUserNotFoundException("belirtilen grupId: "+ groupId + "bulunamadı."));

        return projectUserEntities.stream()
                .map(projectUserEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

}
