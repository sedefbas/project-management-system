package management.sedef.issue.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueAssignmentService;
import management.sedef.issue.model.dto.AssignedUserDTO;
import management.sedef.issue.model.enums.IssueAssignmentType;
import management.sedef.issue.model.request.IssueAssignmentRequest;

import java.util.List;
import java.util.Map;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/issue-assignments")
@RequiredArgsConstructor
public class IssueAssignmentController {

    private final IssueAssignmentService issueAssignmentService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('issue-assignment:create')")
    public SuccessResponse<Void> addIssueAssignment(
            @Valid @RequestBody IssueAssignmentRequest request,
            @RequestHeader("Authorization") String token) {

        issueAssignmentService.addIssueAssignment(request, token);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{issueId}/user/{userId}")
    @PreAuthorize("hasAnyAuthority('issue-assignment:delete')")
    public SuccessResponse<Void> delete(
            @PathVariable Long issueId,
            @PathVariable Long userId) {
        issueAssignmentService.delete(issueId, userId);
        return SuccessResponse.success();
    }

    @PutMapping("/{issueId}/user/{userId}/role")
    @PreAuthorize("hasAnyAuthority('issue-assignment:update')")
    public SuccessResponse<Void> updateRole(
            @PathVariable Long issueId,
            @PathVariable Long userId,
            @RequestParam RoleName roleName) {
        issueAssignmentService.updateRole(issueId, userId, roleName);
        return SuccessResponse.success();
    }


    @GetMapping("/{issueId}/users")
    @PreAuthorize("hasAnyAuthority('issue-assignment:detail')")
    public SuccessResponse<Map<IssueAssignmentType, List<AssignedUserDTO>>> getAssignedUsers(
            @PathVariable Long issueId) {
        Map<IssueAssignmentType, List<AssignedUserDTO>> assignedUsers = 
            issueAssignmentService.getAssignedUsersByIssueId(issueId);
        return SuccessResponse.success(assignedUsers);
    }
}
