package management.sedef.priority.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.priority.model.request.CompanyPriorityRequest;
import management.sedef.priority.service.CompanyPriorityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/company-priority")
@RequiredArgsConstructor
public class CompanyPriorityController {

    private final CompanyPriorityService companyPriorityService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('company-priority:create')")
    public SuccessResponse<Void> addPriorityToCompany(@PathVariable Long companyId, @RequestBody CompanyPriorityRequest request) {
        companyPriorityService.addPriorityToCompany(companyId, request);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{priorityId}")
    @PreAuthorize("hasAnyAuthority('company-priority:delete')")
    public SuccessResponse<Void> removePriorityFromCompany(@PathVariable Long companyId, @PathVariable Long priorityId) {
        companyPriorityService.removePriorityFromCompany(companyId, priorityId);
        return SuccessResponse.success();
    }

}
