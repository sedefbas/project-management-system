package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueEntity;
import management.sedef.issue.model.entity.IssueLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface IssueLinkRepository extends JpaRepository<IssueLinkEntity, Long> {

    List<IssueLinkEntity> findByIssueId(Long issueId);

    Optional<IssueLinkEntity> findByIssueIdAndLinkedIssueId(Long issueId, Long linkedIssueId);

    @Query("SELECT il FROM IssueLinkEntity il " +
            "JOIN FETCH il.linkedIssue " +
            "WHERE il.issue = :issue")
    List<IssueLinkEntity> findAllByIssue(@Param("issue") IssueEntity issue);

}
