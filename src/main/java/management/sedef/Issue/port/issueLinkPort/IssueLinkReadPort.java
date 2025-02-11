package management.sedef.issue.port.issueLinkPort;

import management.sedef.issue.model.IssueLink;

import java.util.List;

public interface IssueLinkReadPort {
    List<IssueLink> getDependencies(Long issueId);
    IssueLink findByIssueIdAndLinkedIssueId(Long issueId, Long linkedIssueId);
}
