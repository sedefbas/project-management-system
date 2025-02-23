package management.sedef.company.controller;


import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.mapper.companymapper.CompanyUserMapper;
import management.sedef.company.model.response.CompanyUserResponse;
import management.sedef.company.service.CompanyUserService;
import management.sedef.user.model.mapper.UserSummaryWithEmailMapper;
import management.sedef.user.model.response.UserSummaryWithEmailResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company")
@RequiredArgsConstructor
public class CompanyUserController {

     private  final CompanyUserService companyUserService;

    @PostMapping("/{companyId}/users/invite")
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<Void> sendUserInvitationToCompany(
            @PathVariable Long companyId,
            @RequestParam String email) {

        String message = companyUserService.sendUserInvitationToCompany(email, companyId);

        return SuccessResponse.success(message);
    }

    @GetMapping("/{companyId}/users/me")
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<CompanyUserResponse>  findByToken(@RequestHeader("Authorization") String token){
        CompanyUser companyUser = companyUserService.findByToken(token);
        CompanyUserResponse companyUserResponse = CompanyUserMapper.mapToCompanyUserResponse(companyUser);
        return SuccessResponse.success(companyUserResponse);
    }


    @GetMapping("/{companyId}/users")
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<List<UserSummaryWithEmailResponse>>  findUsersByCompanyId(@PathVariable Long companyId){
        List<CompanyUser> companyUsers = companyUserService.findByUsersCompanyId(companyId);
        List<UserSummaryWithEmailResponse> userSummaryResponses = UserSummaryWithEmailMapper.mapToUserSummaryWithEmailResponse(companyUsers);
        return SuccessResponse.success(userSummaryResponses);
    }

    @PostMapping("/add/user")
    @PreAuthorize("hasAnyAuthority('company-user:create')")
    public SuccessResponse<Void> create(@RequestParam String token ) {
        companyUserService.addUserToCompany(token);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{companyId}/users/{userId}")
    @PreAuthorize("hasAnyAuthority('company-user:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long companyId, @PathVariable Long userId) {
        companyUserService.delete(companyId,userId);
        return SuccessResponse.success();
    }

}
