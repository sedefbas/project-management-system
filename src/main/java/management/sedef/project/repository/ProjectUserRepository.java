package management.sedef.project.repository;

import management.sedef.project.model.entity.ProjectUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUserEntity,Long> {

    Optional<ProjectUserEntity> findByUserIdAndProjectId(Long userId, Long projectId);
    List<ProjectUserEntity> findByProjectId(Long projectId);
    List<ProjectUserEntity> findByUserId(Long userId);

}
