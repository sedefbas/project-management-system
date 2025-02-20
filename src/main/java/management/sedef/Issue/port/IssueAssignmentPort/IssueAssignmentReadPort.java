package management.sedef.issue.port.IssueAssignmentPort;

import management.sedef.issue.model.IssueAssignment;

import java.util.List;


public interface IssueAssignmentReadPort {
   List<IssueAssignment>  findByIssueId(Long issueId);
   IssueAssignment findByIssueIdAndAssignedUserId(Long issueId, Long userId);

}
