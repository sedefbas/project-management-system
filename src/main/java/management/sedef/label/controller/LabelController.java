package management.sedef.label.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.label.model.Label;
import management.sedef.label.model.request.LabelRequest;
import management.sedef.label.service.LabelService;
import management.sedef.common.model.entity.response.SuccessResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;


    @PostMapping()
    @PreAuthorize("hasAnyAuthority('label:create')")
    public SuccessResponse<Void> save(@RequestBody LabelRequest request){
        labelService.save(request);
        return SuccessResponse.success();
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('label:detail')")
    public SuccessResponse<List<Label>> findLabelsByCompanyId(@PathVariable Long companyId) {
        List<Label> labels = labelService.findLabelsByCompanyId(companyId);
        return SuccessResponse.success(labels);
    }

    @DeleteMapping("/{labelId}")
    @PreAuthorize("hasAnyAuthority('label:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long labelId) {
        labelService.delete(labelId);
        return SuccessResponse.success();
    }

}
