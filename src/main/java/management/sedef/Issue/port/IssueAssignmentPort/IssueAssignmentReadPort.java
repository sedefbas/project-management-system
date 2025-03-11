package management.sedef.issue.port.IssueAssignmentPort;

import management.sedef.issue.model.IssueAssignment;

import java.util.List;

public interface IssueAssignmentReadPort {

   IssueAssignment findByIssueIdAndAssignedUserId(Long issueId, Long userId);

   List<IssueAssignment> findAllByIssueId(Long issueId);

   List<IssueAssignment> findByAssignedUserIdAndProjectId(Long userId, Long projectId);

   IssueAssignment findById(Long id);
}
