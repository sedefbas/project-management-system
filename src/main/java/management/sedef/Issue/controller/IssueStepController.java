package management.sedef.issue.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueStepService;
import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.mapper.issueStep.IssueStepToResponseMapper;
import management.sedef.issue.model.request.IssueStepRequest;
import management.sedef.issue.model.request.IssueStepUpdateRequest;
import management.sedef.issue.model.response.IssueStepResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/v1/issues/{issueId}/steps")
@RequiredArgsConstructor
public class IssueStepController {

    private final IssueStepService issueStepService;
    private final IssueStepToResponseMapper issueStepToResponseMapper;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('issue-step:create')")
    public SuccessResponse<Void> create(
            @Valid @RequestBody IssueStepRequest request,
            @PathVariable Long issueId) {

        issueStepService.create(request, issueId);

        return SuccessResponse.success();
    }

    // IssueStep silme
    @DeleteMapping("/{issueStepId}")
    @PreAuthorize("hasAnyAuthority('issue-step:delete')")
    public SuccessResponse<Void> delete(
            @PathVariable Long issueStepId,
            @RequestHeader("Authorization") String token) {

        issueStepService.delete(issueStepId);
        return SuccessResponse.success();
    }

    // IssueStep'leri listeler
    @GetMapping
    @PreAuthorize("hasAnyAuthority('issue-step:detail')")
    public SuccessResponse<List<IssueStepResponse>> findByIssueId(
            @PathVariable Long issueId) {
        List<IssueStep> issueSteps = issueStepService.findByIssueIdOrderByStepNumberAsc(issueId);
        List<IssueStepResponse> response = issueSteps.stream()
                .map(issueStepToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(response);
    }

    // IssueStep güncelleme
    @PutMapping("/{issueStepId}")
    @PreAuthorize("hasAnyAuthority('issue-step:update')")
    public SuccessResponse<Void> update(
            @PathVariable Long issueStepId,
            @RequestBody IssueStepUpdateRequest request) {

        issueStepService.update(request, issueStepId);
        return SuccessResponse.success();
    }


}
