package management.sedef.issue.port.issueLinkPort;

import management.sedef.issue.model.IssueLink;

public interface IssueLinkDeletePort {
   IssueLink removeDependency(IssueLink issueLink);
}
