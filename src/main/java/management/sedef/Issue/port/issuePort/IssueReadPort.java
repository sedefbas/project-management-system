package management.sedef.issue.port.issuePort;

import management.sedef.issue.model.Issue;

import java.util.List;

public interface IssueReadPort {
    List<Issue> findIssuesByProject(Long projectId);
    List<Issue> findByStageIdAndProjectId(Long stageId, Long projectId);
    Issue findById(Long issueId);
}
