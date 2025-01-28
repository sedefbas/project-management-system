package management.sedef.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.mapper.companymapper.CompanyUserMapper;
import management.sedef.company.model.request.CompanyUserRequest;
import management.sedef.company.model.response.CompanyUserResponse;
import management.sedef.company.service.CompanyUserService;
import management.sedef.user.model.mapper.UserSummaryMapper;
import management.sedef.user.model.response.UserSummaryResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/users")
@RequiredArgsConstructor
public class CompanyUserController {

     private  final CompanyUserService companyUserService;


    @GetMapping("/me")
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<CompanyUserResponse>  findByToken(@RequestHeader("Authorization") String token){
        CompanyUser companyUser = companyUserService.findByToken(token);
        CompanyUserResponse companyUserResponse = CompanyUserMapper.mapToCompanyUserResponse(companyUser);
        return SuccessResponse.success(companyUserResponse);
    }


    @GetMapping()
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<List<UserSummaryResponse>>  findByCompanyId(@PathVariable Long companyId){
        List<CompanyUser> companyUsers = companyUserService.findByCompanyId(companyId);
        List<UserSummaryResponse> userSummaryResponses = UserSummaryMapper.mapToUserSummaryResponse(companyUsers);
        return SuccessResponse.success(userSummaryResponses);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('company-user:create')")
    public SuccessResponse<Void> create(@RequestBody @Valid CompanyUserRequest companyUserRequest, @PathVariable Long companyId) {
        companyUserService.create(companyUserRequest,companyId);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('company-user:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long companyId, @PathVariable Long userId) {
        companyUserService.delete(companyId,userId);
        return SuccessResponse.success();
    }

}
