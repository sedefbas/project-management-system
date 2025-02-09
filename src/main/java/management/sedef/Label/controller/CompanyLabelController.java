package management.sedef.Label.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.Label.model.request.CompanyLabelRequest;
import management.sedef.Label.service.CompanylabelService;
import management.sedef.common.model.entity.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/company-label")
@RequiredArgsConstructor
public class CompanyLabelController {

    private final CompanylabelService companyLabelService;

    @PostMapping()
    @PreAuthorize("hasAnyAuthority('company-label:create')")
    public SuccessResponse<Void> addLabelToCompany(@PathVariable Long companyId, @RequestBody CompanyLabelRequest request) {
        companyLabelService.addLabelToCompany(companyId, request);
        return SuccessResponse.success();
    }


    @DeleteMapping("/{labelId}")
    @PreAuthorize("hasAnyAuthority('company-label:delete')")
    public SuccessResponse<Void> removeLabelFromCompany(@PathVariable Long companyId, @PathVariable Long labelId) {
        companyLabelService.removeLabelFromCompany(companyId, labelId);
        return SuccessResponse.success();
    }
}