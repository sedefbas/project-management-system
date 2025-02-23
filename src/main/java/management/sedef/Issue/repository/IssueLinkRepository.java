package management.sedef.issue.repository;


import management.sedef.issue.model.entity.IssueLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;


public interface IssueLinkRepository extends JpaRepository<IssueLinkEntity, Long> {

    List<IssueLinkEntity> findByIssueId(Long issueId);
    Optional<IssueLinkEntity> findByIssueIdAndLinkedIssueId(Long issueId, Long linkedIssueId);

}
