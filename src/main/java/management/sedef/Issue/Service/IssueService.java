package management.sedef.issue.Service;

import management.sedef.issue.model.Issue;
import management.sedef.issue.model.request.IssueRequest;
import management.sedef.stage.model.enums.StageType;
import java.util.List;


public interface IssueService {
    void create(IssueRequest request,Long companyId, Long projectId, String token);
    void delete(Long issueId);
    List<Issue> findIssuesByProject(Long projectId);
    List<Issue> findByStageIdAndProjectId(Long stageId, Long projectId);
    Issue findById(Long issueId);
    void updateStage(Long issueId, StageType type);
    void updateLabel(Long issueId, Long labelId );
    void updatePriority(Long issueId, Long priorityId );
}
