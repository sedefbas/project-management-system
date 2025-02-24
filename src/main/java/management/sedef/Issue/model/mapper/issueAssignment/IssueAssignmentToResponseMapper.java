package management.sedef.issue.model.mapper.issueAssignment;

import java.sql.Date;

import org.springframework.stereotype.Component;

import management.sedef.auth.model.enums.RoleName;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.response.AssignedIssueResponse;


@Component
public class IssueAssignmentToResponseMapper {

    public AssignedIssueResponse map(IssueAssignment issueAssignment) {
        Issue issue = issueAssignment.getIssue();
        return AssignedIssueResponse.builder()
                .issueId(issue.getId())
                .issueName(issue.getName())
                .explanation(issue.getExplanation())
                .startDate(issue.getStartDate() != null ? new Date(issue.getStartDate().getTime()) : null)
                .deadLineDate(issue.getDeadLineDate() != null ? new Date(issue.getDeadLineDate().getTime()) : null)
                .assignmentRole(RoleName.valueOf(issueAssignment.getRole().getName()))
                .stageName(issue.getStage() != null ? issue.getStage().getName().name() : null)
                .priorityName(issue.getPriority() != null ? issue.getPriority().getName() : null)
                .labelName(issue.getLabel() != null ? issue.getLabel().getName() : null)
                .build();
    }
}