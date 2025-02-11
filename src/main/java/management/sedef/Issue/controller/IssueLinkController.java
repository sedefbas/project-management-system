package management.sedef.issue.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.issue.Service.IssueLinkService;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.enums.IssueLinkType;
import management.sedef.issue.model.mapper.issue.IssueToIssueSummaryResponse;
import management.sedef.issue.model.mapper.issueLink.IssueLinkToResponseMapper;
import management.sedef.issue.model.request.IssueLinkRequest;
import management.sedef.issue.model.response.IssueLinkResponse;
import management.sedef.issue.model.response.IssueSummaryResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/issues")
@RequiredArgsConstructor
public class IssueLinkController {

    private final IssueLinkService issueLinkService;
    private final IssueLinkToResponseMapper issueLinkToResponseMapper;
    private final IssueToIssueSummaryResponse issueToIssueSummaryResponse = IssueToIssueSummaryResponse.initialize();

    @GetMapping("/{issueId}/dependencies")
    @PreAuthorize("hasAnyAuthority('issue:detail')")
    public SuccessResponse<List<IssueLinkResponse>> getDependencies(@PathVariable Long issueId) {
        List<IssueLink> issueLinks = issueLinkService.getDependencies(issueId);
        List<IssueLinkResponse> responses = issueLinks.stream()
                .map(issueLinkToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(responses);
    }

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('issue:create')")
    public SuccessResponse<Void> addDependency(@RequestBody IssueLinkRequest request) {
        issueLinkService.addDependency(request);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{issueId}/dependencies/{dependentIssueId}")
    @PreAuthorize("hasAnyAuthority('issue:delete')")
    public SuccessResponse<Void> removeDependency(@PathVariable Long issueId, @PathVariable Long dependentIssueId) {
        issueLinkService.removeDependency(issueId, dependentIssueId);
        return SuccessResponse.success();
    }

    @GetMapping("/{issueId}/group-dependencies")
    @PreAuthorize("hasAnyAuthority('issue:detail')")
    public SuccessResponse<Map<IssueLinkType, Set<IssueSummaryResponse>>> getLinkedIssues(@PathVariable Long issueId) {

        Map<IssueLinkType, Set<Issue>> linkedIssues = issueLinkService.getLinkedIssues(issueId);

        Map<IssueLinkType, Set<IssueSummaryResponse>> linkedIssuesSummary = new HashMap<>();

        for (Map.Entry<IssueLinkType, Set<Issue>> entry : linkedIssues.entrySet()) {
            Set<IssueSummaryResponse> summaryResponses = entry.getValue().stream()
                    .map(issue -> issueToIssueSummaryResponse.map(issue)) // Mapper ile dönüşüm
                    .collect(Collectors.toSet());

            linkedIssuesSummary.put(entry.getKey(), summaryResponses);
        }

        // Sonuç olarak dönüyoruz
        return SuccessResponse.success(linkedIssuesSummary);
    }

}
