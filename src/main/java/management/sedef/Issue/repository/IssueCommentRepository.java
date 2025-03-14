package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueCommentRepository extends JpaRepository<IssueCommentEntity,Long> {

    List<IssueCommentEntity> findAllByIssueIdOrderByCreatedAtDesc(Long issueId);

    List<IssueCommentEntity> findAllByAuthorIdOrderByCreatedAtDesc(Long authorId);

    List<IssueCommentEntity> findAllByParentCommentIdOrderByCreatedAtAsc(Long parentCommentId);

}
