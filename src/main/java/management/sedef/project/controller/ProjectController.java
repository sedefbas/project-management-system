package management.sedef.project.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.Group;
import management.sedef.company.model.mapper.groupmapper.GroupToGroupResponseMapper;
import management.sedef.company.model.response.GroupResponse;
import management.sedef.project.model.Project;
import management.sedef.project.model.mapper.project.ProjectToResponseMapper;
import management.sedef.project.model.request.ProjectRequest;
import management.sedef.project.model.request.ProjectUpdateRequest;
import management.sedef.project.model.response.ProjectResponse;
import management.sedef.project.service.ProjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService service;
    private final ProjectToResponseMapper projectToResponseMapper;
    private final GroupToGroupResponseMapper groupToGroupResponseMapper;


    @PostMapping()
    @PreAuthorize("hasAnyAuthority('project:create')")
    public SuccessResponse<Void> create(@RequestBody ProjectRequest request, @PathVariable Long companyId){
        service.create(request, companyId);
        return SuccessResponse.success();
    }


    @DeleteMapping("/{projectId}")
    @PreAuthorize("hasAnyAuthority('project:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long companyId, @PathVariable Long projectId) {
        service.delete(companyId, projectId);
        return SuccessResponse.success();
    }

    // Proje güncelleme
    @PutMapping("/{projectId}")
    @PreAuthorize("hasAnyAuthority('project:update')")
    public SuccessResponse<Void> update(
            @PathVariable Long companyId,
            @PathVariable Long projectId,
            @RequestBody ProjectUpdateRequest request
    ) {
        service.update(projectId, companyId, request);
        return SuccessResponse.success();
    }


    // Projeyi ID ve Company ID'ye göre bulma
    @GetMapping("/{projectId}")
    @PreAuthorize("hasAnyAuthority('project:detail')")
    public SuccessResponse<ProjectResponse> getById(
            @PathVariable Long companyId,
            @PathVariable Long projectId
    ) {
        Project project = service.findByIdAndCompanyId(projectId, companyId);
        return SuccessResponse.success(  projectToResponseMapper.map(project));
    }



    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('project:detail')")
    public SuccessResponse<List<ProjectResponse>> getAllProjectsByCompanyId(@PathVariable Long companyId) {
        List<Project> projects = service.getAllProjectsByCompanyId(companyId);

        List<ProjectResponse> projectResponses = projects.stream()
                .map(projectToResponseMapper::map)
                .collect(Collectors.toList());

        return SuccessResponse.success(projectResponses);
    }

//    @PostMapping("/{projectId}/groups/{groupId}")
//    @PreAuthorize("hasAnyAuthority('project:update')")
//    public SuccessResponse<Void> addGroupToProject(
//            @PathVariable Long companyId,
//            @PathVariable Long projectId,
//            @PathVariable Long groupId
//    ) {
//        service.addGroupToProject(companyId, projectId, groupId);
//        return SuccessResponse.success();
//    }
//
//    @DeleteMapping("/{projectId}/groups/{groupId}")
//    @PreAuthorize("hasAnyAuthority('project:update')")
//    public SuccessResponse<Void> removeGroupFromProject(
//            @PathVariable Long companyId,
//            @PathVariable Long projectId,
//            @PathVariable Long groupId
//    ) {
//        service.removeGroupFromProject(companyId, projectId, groupId);
//        return SuccessResponse.success();
//    }

}
