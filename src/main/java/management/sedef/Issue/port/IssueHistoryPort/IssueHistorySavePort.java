package management.sedef.issue.port.IssueHistoryPort;

import management.sedef.issue.model.IssueHistory;

public interface IssueHistorySavePort {
    IssueHistory save(IssueHistory issueHistory);
}
