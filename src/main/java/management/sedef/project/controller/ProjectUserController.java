package management.sedef.project.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.request.ProjectUserRequest;
import management.sedef.project.model.request.ProjectUserUpdateRequest;
import management.sedef.project.service.ProjectUserService;
import management.sedef.user.model.mapper.UserSummaryMapper;
import management.sedef.user.model.response.UserSummaryResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project")
@RequiredArgsConstructor
public class ProjectUserController {

    private final ProjectUserService service;
    private final UserSummaryMapper userSummaryMapper;


    @PutMapping("/{projectId}/user/{userId}")
    @PreAuthorize("hasAnyAuthority('project-user:update')")
    public SuccessResponse<Void> updateUserInProject(@PathVariable Long userId,
                                                     @PathVariable Long projectId,
                                                     @PathVariable Long companyId,
                                                     @RequestBody ProjectUserUpdateRequest request) {
        service.updateUserInProject(request,userId, projectId, companyId);
        return SuccessResponse.success();
    }

    @GetMapping("/my-projects")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<List<UserSummaryResponse>> getProjectsByToken(@RequestHeader("Authorization") String token,
                                                                         @PathVariable Long companyId) {
        // Token'dan userId'yi al
        List<ProjectUser> users = service.getProjectByToken(token);  // Token ile projeleri al
        List<UserSummaryResponse> response = userSummaryMapper.mapToUserSummaryResponse(users);
        return SuccessResponse.success(response);
    }

    @GetMapping("/{projectId}/users")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<List<UserSummaryResponse>> getUsersForProject(@PathVariable Long companyId, @PathVariable Long projectId) {
        List<ProjectUser> users = service.getUsersForProject(projectId);
        List<UserSummaryResponse> response = userSummaryMapper.mapToUserSummaryResponse(users);
        return SuccessResponse.success(response);
    }


    @DeleteMapping("/{projectId}/user/{userId}")
    @PreAuthorize("hasAnyAuthority('project:delete')")
    public SuccessResponse<Void> removeUserFromProject(@PathVariable Long userId,
                                                       @PathVariable Long projectId,
                                                       @PathVariable Long companyId) {
        service.removeUserFromProjectById(userId,projectId);
        return SuccessResponse.success();
    }


    @PostMapping("/{projectId}/user/send")
    @PreAuthorize("hasAnyAuthority('project:create')")
    public SuccessResponse<Void> sendUserInvitationForProject(@RequestBody ProjectUserRequest request,
                                                    @PathVariable Long projectId,
                                                    @PathVariable Long companyId,
                                                    @RequestParam String email){
        service.sendUserInvitationForProject(request,projectId,companyId,email);
        return SuccessResponse.success();
    }


    @PostMapping("/{projectId}/user/add")
    @PreAuthorize("hasAnyAuthority('project:create')")
    public SuccessResponse<Void> addUserToProjectByToken(@RequestParam String token){
        service.addUserToProjectByToken(token);
        return SuccessResponse.success();
    }



}
