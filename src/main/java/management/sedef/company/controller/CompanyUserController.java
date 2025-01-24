package management.sedef.company.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.common.model.request.TokenRequest;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.mapper.companymapper.CompanyUserMapper;
import management.sedef.company.model.request.CompanyUserDeleteRequest;
import management.sedef.company.model.request.CompanyUserRequest;
import management.sedef.company.model.response.CompanyUserResponse;
import management.sedef.company.service.CompanyUserService;
import management.sedef.user.model.mapper.UserSummaryMapper;
import management.sedef.user.model.response.UserSummaryResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/company-user")
@RequiredArgsConstructor
public class CompanyUserController {

     private  final CompanyUserService companyUserService;

    //Çalıştığı şirket bilgisini getirir.
    @GetMapping()
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<CompanyUserResponse>  findByToken(@RequestBody TokenRequest request){
        CompanyUser companyUser = companyUserService.findByToken(request);
        CompanyUserResponse companyUserResponse = CompanyUserMapper.mapToCompanyUserResponse(companyUser);
        return SuccessResponse.success(companyUserResponse);
    }

    //üstteki methoddan id bulunduktan sonra burada kullanılır.
    @GetMapping("/users")
    @PreAuthorize("hasAnyAuthority('company-user:detail')")
    public SuccessResponse<List<UserSummaryResponse>>  findByCompanyId(@RequestParam   Long companyId){
        List<CompanyUser> companyUsers = companyUserService.findByCompanyId(companyId);
        List<UserSummaryResponse> userSummaryResponses = UserSummaryMapper.mapToUserSummaryResponse(companyUsers);
        return SuccessResponse.success(userSummaryResponses);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('company-user:create')")
    public SuccessResponse<Void> create(@RequestBody @Valid CompanyUserRequest companyUserRequest) {
        companyUserService.create(companyUserRequest);
        return SuccessResponse.success();
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('company-user:delete')")
    public SuccessResponse<Void> delete(@RequestBody @Valid CompanyUserDeleteRequest userDeleteRequest) {
        companyUserService.delete(userDeleteRequest);
        return SuccessResponse.success();
    }

}
