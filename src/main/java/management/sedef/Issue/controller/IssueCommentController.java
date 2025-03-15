package management.sedef.issue.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueCommentService;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.mapper.issueComment.IssueCommentToResponseMapper;
import management.sedef.issue.model.request.IssueCommentRequest;
import management.sedef.issue.model.request.IssueCommentUpdateRequest;
import management.sedef.issue.model.response.IssueCommentResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project/{projectId}/issues/{issueId}/comments")
@RequiredArgsConstructor
public class IssueCommentController {

    private final IssueCommentService issueCommentService;
    private final IssueCommentToResponseMapper issueCommentToResponseMapper;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('issue-comment:create')")
    public SuccessResponse<Void> create(
            @Valid @RequestBody IssueCommentRequest request,
            @RequestHeader("Authorization") String token) {

        issueCommentService.saveComment(request, token);
        return SuccessResponse.success();
    }


    @DeleteMapping("/{commentId}")
    @PreAuthorize("hasAnyAuthority('issue-comment:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long commentId) {
        issueCommentService.deleteComment(commentId);
        return SuccessResponse.success();
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('issue-comment:list')")
    public SuccessResponse<List<IssueCommentResponse>> findCommentsByIssue(@PathVariable Long issueId) {
        List<IssueComment> comments = issueCommentService.getCommentsByIssueId(issueId);
        List<IssueCommentResponse> commentResponses = comments.stream()
                .map(issueCommentToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(commentResponses);
    }


    @GetMapping("/{commentId}")
    @PreAuthorize("hasAnyAuthority('issue-comment:detail')")
    public SuccessResponse<IssueCommentResponse> findById(@PathVariable Long commentId) {
        IssueComment comment = issueCommentService.findById(commentId);
        IssueCommentResponse commentResponse = issueCommentToResponseMapper.map(comment);
        return SuccessResponse.success(commentResponse);
    }


    @PatchMapping("/{commentId}")
    @PreAuthorize("hasAnyAuthority('issue-comment:update')")
    public SuccessResponse<Void> update(
            @PathVariable Long commentId,
            @Valid @RequestBody IssueCommentUpdateRequest request) {
        issueCommentService.updateComment(commentId,request);
        return SuccessResponse.success("Comment updated successfully.");
    }

}