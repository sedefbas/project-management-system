package management.sedef.issue.Service;

import management.sedef.issue.model.Issue;
import management.sedef.issue.model.request.IssueRequest;

import java.util.List;

public interface IssueService {
    void create(IssueRequest request);
    void delete(Long issueId);
    List<Issue> findIssuesByProject(Long projectId);
    List<Issue> findByStageIdAndProjectId(Long stageId, Long projectId);
    Issue findById(Long issueId);
}
