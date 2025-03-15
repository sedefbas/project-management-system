package management.sedef.issue.Service;

import management.sedef.issue.model.IssueHistory;
import management.sedef.issue.model.request.IssueHistoryRequest;

public interface IssueHistoryService {
    void delete(Long historyId);
    IssueHistory save(IssueHistoryRequest issueHistory);
}
