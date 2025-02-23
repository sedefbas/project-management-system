package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueAssignmentRepository extends JpaRepository<IssueAssignmentEntity, Long> {

    List<IssueAssignmentEntity> findByIssueId(Long issueId);
    Optional<IssueAssignmentEntity> findByIssueIdAndAssignedUserId(Long issueId, Long userId);
    List<IssueAssignmentEntity> findAllByIssueId(Long issueId);

}
