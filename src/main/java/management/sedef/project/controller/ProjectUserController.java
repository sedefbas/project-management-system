package management.sedef.project.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.project.model.ProjectUser;
import management.sedef.project.model.mapper.projectUser.DomainToUserListForProjectResponseMapper;
import management.sedef.project.model.request.ProjectUserRequest;
import management.sedef.project.model.response.UserListForProjectResponse;
import management.sedef.project.service.ProjectUserService;
import management.sedef.user.model.mapper.UserSummaryMapper;
import management.sedef.user.model.response.UserSummaryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/project")
@RequiredArgsConstructor
public class ProjectUserController {

    private final ProjectUserService service;
    private final UserSummaryMapper userSummaryMapper;
    private final DomainToUserListForProjectResponseMapper userListForProjectResponseMapper;


    //projeye göre user listeleme
    @GetMapping("/{projectId}/users")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<List<UserListForProjectResponse>> getUsersForProject(@PathVariable Long companyId, @PathVariable Long projectId) {
        List<ProjectUser> users = service.getUsersForProject(projectId);
        List<UserListForProjectResponse> response = userListForProjectResponseMapper.map(users);
        return SuccessResponse.success(response);
    }

    // Kullanıcıları grup id'ye göre al
    @GetMapping("/{projectId}/group/{groupId}/users")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<List<UserListForProjectResponse>> getUsersByGroupId(@PathVariable Long companyId, @PathVariable Long projectId, @PathVariable Long groupId) {
        List<ProjectUser> users = service.getUsersBygroupId(groupId);
        List<UserListForProjectResponse> response = userListForProjectResponseMapper.map(users);
        return SuccessResponse.success(response);
    }

    // Kullanıcıları alt grup id'ye göre al
    @GetMapping("/{projectId}/subgroup/{subgroupId}/users")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<List<UserListForProjectResponse>> getUsersBySubGroupId(@PathVariable Long companyId, @PathVariable Long projectId, @PathVariable Long subgroupId) {
        List<ProjectUser> users = service.getUsersBySubGroupId(subgroupId);
        List<UserListForProjectResponse> response = userListForProjectResponseMapper.map(users);
        return SuccessResponse.success(response);
    }


   //userın çalıştığı projeler
    @GetMapping("/my-projects")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<List<UserSummaryResponse>> getProjectsByToken(@RequestHeader("Authorization") String token,
                                                                         @PathVariable Long companyId) {
        // Token'dan userId'yi al
        List<ProjectUser> users = service.getProjectByToken(token);  // Token ile projeleri al
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
    public SuccessResponse<Void> addUserToProjectByToken(@RequestParam String token,@RequestHeader("Authorization") String adminToken){
        service.addUserToProjectByToken(token,adminToken);
        return SuccessResponse.success();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('project-user:detail')")
    public SuccessResponse<Page<UserSummaryResponse>> searchProjectUsers(
            @RequestParam Long projectId,
            @RequestParam String searchTerm,
            @RequestParam(defaultValue = "0") int page,  // Varsayılan değer 0
            @RequestParam(defaultValue = "10") int size) {  // Varsayılan değer 10

        Pageable pageable = PageRequest.of(page, size);
        Page<ProjectUser> usersPage = service.getUsersForProjectWithSearch(projectId, searchTerm, pageable);
        List<UserSummaryResponse> userSummaryList = userSummaryMapper.mapToUserSummaryResponse(usersPage.getContent());
        Page<UserSummaryResponse> responsePage = new PageImpl<>(userSummaryList, pageable, usersPage.getTotalElements());
        return SuccessResponse.success(responsePage);
    }

//    @PutMapping("/{projectId}/user/{userId}")
//    @PreAuthorize("hasAnyAuthority('project-user:update')")
//    public SuccessResponse<Void> updateUserInProject(@PathVariable Long userId,
//                                                     @PathVariable Long projectId,
//                                                     @PathVariable Long companyId,
//                                                     @RequestBody ProjectUserUpdateRequest request) {
//        service.updateUserInProject(request,userId, projectId, companyId);
//        return SuccessResponse.success();
//    }



}
