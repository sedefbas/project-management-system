package management.sedef.project.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.project.model.request.ProjectRequest;
import management.sedef.project.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService service;


    @PostMapping()
    @PreAuthorize("hasAnyAuthority('project:create')")
    public SuccessResponse<Void> create(@RequestBody ProjectRequest request, @PathVariable Long companyId){
        service.create(request, companyId);
        return SuccessResponse.success();
    }

}
