package management.sedef.issue.port.IssueHistoryPort;


import management.sedef.issue.model.IssueHistory;

public interface IssueHistoryReadPort {
    IssueHistory findById(Long id);
}
