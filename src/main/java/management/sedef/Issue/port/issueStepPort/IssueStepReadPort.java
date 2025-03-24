package management.sedef.issue.port.issueStepPort;


import management.sedef.issue.model.IssueStep;

import java.util.List;

public interface IssueStepReadPort {
    IssueStep findById(Long issueStepId);
    List<IssueStep> findByIssueIdOrderByStepNumberAsc(Long issueId);
    Integer findMaxStepNumberByIssueId(Long issueId);
}
