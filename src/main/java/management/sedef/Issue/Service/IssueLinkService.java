package management.sedef.issue.Service;

import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.request.IssueLinkRequest;

import java.util.List;

public interface IssueLinkService {
    List<IssueLink>getDependencies(Long issueId);
    void addDependency(IssueLinkRequest request);
    void removeDependency(Long issueId,Long dependentIssueId);
}
