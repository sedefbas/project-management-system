package management.sedef.issue.Service;

import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.request.IssueStepRequest;
import management.sedef.issue.model.request.IssueStepUpdateRequest;

import java.util.List;

public interface IssueStepService {
    void create(IssueStepRequest request,Long issueId);
    void delete(Long issueStepId);
    List<IssueStep> findByIssueIdOrderByStepNumberAsc(Long issueId);
    void update(IssueStepUpdateRequest request, Long issueStepId);

}
