package management.sedef.priority.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.request.PriorityRequest;
import management.sedef.priority.service.PriorityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/priorities")
@RequiredArgsConstructor
public class PriorityController {

    private final PriorityService priorityService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('priority:create')")
    public SuccessResponse<Void> save(@RequestBody PriorityRequest request) {
        priorityService.save(request);
        return SuccessResponse.success();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('priority:detail')")
    public SuccessResponse<List<Priority>> findPrioritiesByCompanyId(@PathVariable Long companyId) {
        List<Priority> priorities = priorityService.findPrioritiesByCompanyId(companyId);
        return SuccessResponse.success(priorities);
    }

    @DeleteMapping("/{priorityId}")
    @PreAuthorize("hasAnyAuthority('priority:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long priorityId) {
        priorityService.delete(priorityId);
        return SuccessResponse.success();
    }
}
