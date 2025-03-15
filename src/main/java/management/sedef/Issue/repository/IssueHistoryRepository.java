package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueHistoryRepository extends JpaRepository<IssueHistoryEntity,Long> {
}
