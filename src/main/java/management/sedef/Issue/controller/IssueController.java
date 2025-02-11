package management.sedef.issue.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueService;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.mapper.issue.IssueToResponseMapper;
import management.sedef.issue.model.request.IssueRequest;
import management.sedef.issue.model.response.IssueResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project/{projectId}/issues")
@RequiredArgsConstructor
public class IssueController {

    private final IssueService issueService;
    private final IssueToResponseMapper issueToResponseMapper;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('issue:create')")
    public SuccessResponse<Void> create(@RequestBody IssueRequest request,@PathVariable Long companyId, @PathVariable Long projectId) {
        issueService.create(request,companyId,projectId);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{issueId}")
    @PreAuthorize("hasAnyAuthority('issue:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long issueId) {
        issueService.delete(issueId);
        return SuccessResponse.success();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('issue:detail')")
    public SuccessResponse<List<IssueResponse>> findIssuesByProject(@PathVariable Long projectId) {
        List<Issue> issues = issueService.findIssuesByProject(projectId);
        List<IssueResponse> issueResponses = issues.stream()
                .map(issueToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(issueResponses);
    }

    @GetMapping("/{issueId}")
    @PreAuthorize("hasAnyAuthority('issue:detail')")
    public SuccessResponse<IssueResponse> findById(@PathVariable Long issueId) {
        Issue issue = issueService.findById(issueId);
        IssueResponse issueResponse = issueToResponseMapper.map(issue);
        return SuccessResponse.success(issueResponse);
    }

    @GetMapping("/stage/{stageId}")
    @PreAuthorize("hasAnyAuthority('issue:detail')")
    public SuccessResponse<List<IssueResponse>> findByStageIdAndProjectId(
            @PathVariable Long projectId,
            @PathVariable Long stageId) {
        List<Issue> issues = issueService.findByStageIdAndProjectId(stageId, projectId);
        List<IssueResponse> issueResponses = issues.stream()
                .map(issueToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(issueResponses);
    }


}
