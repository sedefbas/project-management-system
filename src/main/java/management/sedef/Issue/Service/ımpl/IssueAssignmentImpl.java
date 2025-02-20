package management.sedef.issue.Service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.RoleNotFoundByNameException;
import management.sedef.auth.model.Role;
import management.sedef.auth.port.RoleReadPort;
import management.sedef.issue.Service.IssueAssignmentService;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.mapper.issueAssignment.IssueAssignmentRequestToDomainMapper;
import management.sedef.issue.model.request.IssueAssignmentRequest;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentDeletePort;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentReadPort;
import management.sedef.issue.port.IssueAssignmentPort.IssueAssignmentSavePort;
import management.sedef.user.model.User;
import management.sedef.user.service.UserEmailService;
import management.sedef.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueAssignmentImpl implements IssueAssignmentService {

    private final IssueAssignmentDeletePort deletePort;
    private final IssueAssignmentReadPort readPort;
    private final IssueAssignmentSavePort savePort;
    private final UserService userService;
    private final IssueAssignmentRequestToDomainMapper assignmentRequestToDomainMapper;
    private final RoleReadPort roleReadPort;
    private final UserEmailService userEmailService;


    @Override
    public void addIssueAssignment(IssueAssignmentRequest request, String token) {

        User assignedBy = userService.getUserFromToken(token);

        Role role = roleReadPort.findByName(request.getRole())
                .orElseThrow(() -> new RoleNotFoundByNameException(request.getRole().name()));

        IssueAssignment issueAssignment = assignmentRequestToDomainMapper.map(request);
        issueAssignment.setAssignedBy(assignedBy);
        issueAssignment.setRole(role);

        savePort.save(issueAssignment);
        userEmailService.reportIssue(issueAssignment);
    }

    @Override
    public void delete(Long issueId, Long userId) {
        IssueAssignment issueAssignment = readPort.findByIssueIdAndAssignedUserId(issueId,userId);
        deletePort.delete(issueAssignment);
    }

}
