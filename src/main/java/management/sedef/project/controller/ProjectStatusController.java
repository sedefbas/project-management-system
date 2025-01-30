package management.sedef.project.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.project.model.enums.ProjectStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/project-status")
@RequiredArgsConstructor
public class ProjectStatusController {

    @GetMapping
    @PreAuthorize("hasAnyAuthority('project-status:list')")
    public SuccessResponse<List<ProjectStatus>> getAllStatuses() {
        return SuccessResponse.success(Arrays.asList(ProjectStatus.values()));
    }

}
