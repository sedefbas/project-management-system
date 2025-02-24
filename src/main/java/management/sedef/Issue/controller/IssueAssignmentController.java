package management.sedef.issue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueAssignmentService;
import management.sedef.issue.model.dto.AssignedUserDTO;
import management.sedef.issue.model.enums.IssueAssignmentType;
import management.sedef.issue.model.request.IssueAssignmentRequest;
import management.sedef.issue.model.response.AssignedIssueResponse;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.mapper.issueAssignment.IssueAssignmentToResponseMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/issue-assignments")
@RequiredArgsConstructor
public class IssueAssignmentController {

    private final IssueAssignmentService issueAssignmentService;
    private final IssueAssignmentToResponseMapper mapper;

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
        Map<IssueAssignmentType, List<AssignedUserDTO>> assignedUsers = issueAssignmentService
                .getAssignedUsersByIssueId(issueId);
        return SuccessResponse.success(assignedUsers);
    }

    @GetMapping("/user/{userId}/project/{projectId}")
    @PreAuthorize("hasAnyAuthority('issue-assignment:detail')")
    public SuccessResponse<List<AssignedIssueResponse>> getAssignmentsByUserIdAndProjectId(
            @PathVariable Long userId,
            @PathVariable Long projectId) {

        List<IssueAssignment> assignments = issueAssignmentService.getAssignmentsByUserIdAndProjectId(userId,
                projectId);

        List<AssignedIssueResponse> response = assignments.stream()
                .map(mapper::map)
                .collect(Collectors.toList());

        return SuccessResponse.success(response);
    }
}
