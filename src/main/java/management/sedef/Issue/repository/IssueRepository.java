package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<IssueEntity,Long> {
    List<IssueEntity> findByProjectId(Long projectId);
    List<IssueEntity> findByStageIdAndProjectId(Long stageId, Long projectId);
}
