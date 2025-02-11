package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueRepository extends JpaRepository<IssueEntity,Long> {

    List<IssueEntity> findByProjectId(Long projectId);

    List<IssueEntity> findByStageIdAndProjectId(Long stageId, Long projectId);

    @Query("SELECT COUNT(i) FROM IssueEntity i WHERE i.project.id = :projectId")
    Long countByProjectId(@Param("projectId") Long projectId);
}
