package management.sedef.issue.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueAssignmentService;
import management.sedef.issue.model.request.IssueAssignmentRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project/{projectId}/issues-assignment")
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

}
