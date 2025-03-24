package management.sedef.issue.repository;

import management.sedef.issue.model.entity.IssueStepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IssueStepRepository extends JpaRepository<IssueStepEntity,Long> {

    List<IssueStepEntity> findByIssueIdOrderByStepNumberAsc(Long issueId);

    @Query("SELECT COALESCE(MAX(i.stepNumber), 0) FROM IssueStepEntity i WHERE i.issue.id = :issueId")
    Integer findMaxStepNumberByIssueId(@Param("issueId") Long issueId);
}
