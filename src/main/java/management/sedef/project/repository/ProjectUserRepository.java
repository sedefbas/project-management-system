package management.sedef.project.repository;

import management.sedef.project.model.entity.ProjectUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectUserRepository extends JpaRepository<ProjectUserEntity,Long> {

    Optional<ProjectUserEntity> findByUserIdAndProjectId(Long userId, Long projectId);
    List<ProjectUserEntity> findByProjectId(Long projectId);
    List<ProjectUserEntity> findByUserId(Long userId);
    boolean existsByUserIdAndProjectId(Long userId, Long projectId);
    Optional<List<ProjectUserEntity>> findBySubGroupId(Long subGroupId);
    Optional<List<ProjectUserEntity>> findByGroupId(Long groupId);

    @Query("SELECT COUNT(pu) FROM ProjectUserEntity pu WHERE pu.project.id = :projectId")
    int countUsersByProjectId(@Param("projectId") Long projectId);

}
