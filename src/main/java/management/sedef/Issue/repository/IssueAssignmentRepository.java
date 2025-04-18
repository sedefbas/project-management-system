package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueAssignmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IssueAssignmentRepository extends JpaRepository<IssueAssignmentEntity, Long> {

    Optional<IssueAssignmentEntity> findByIssueIdAndAssignedUserId(Long issueId, Long userId);

    List<IssueAssignmentEntity> findAllByIssueId(Long issueId);

    @Query("SELECT ia FROM IssueAssignmentEntity ia WHERE ia.assignedUser.id = :userId AND ia.issue.project.id = :projectId")
    List<IssueAssignmentEntity> findAllByAssignedUserIdAndIssueProjectId(
            @Param("userId") Long userId,
            @Param("projectId") Long projectId);

}
