package management.sedef.issue.Service;

import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.enums.IssueLinkType;
import management.sedef.issue.model.request.IssueLinkRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IssueLinkService {
    List<IssueLink>getDependencies(Long issueId);
    void addDependency(IssueLinkRequest request);
    void removeDependency(Long issueId,Long dependentIssueId);
    Map<IssueLinkType,Set<Issue>> getLinkedIssues(Long issueId);
}
