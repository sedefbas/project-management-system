package management.sedef.company.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.Company;
import management.sedef.company.model.mapper.companymapper.CompanyToResponseMapper;
import management.sedef.company.model.request.CompanyRequest;
import management.sedef.company.model.request.CompanyUpdateRequest;
import management.sedef.company.model.response.CompanyResponse;
import management.sedef.company.model.response.CompanySummaryResponse;
import management.sedef.company.service.CompanyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyToResponseMapper companyToResponseMapper ;

    // ADMIN ve COMPANY_OWNER için şirket oluşturma yetkisi
    @PostMapping
    @PreAuthorize("hasAnyAuthority('company:create')")
    public SuccessResponse<Void> create(@RequestBody @Valid CompanyRequest request,
                                        @RequestHeader("Authorization") String token) {
        companyService.create(request, token);
        return SuccessResponse.success();
    }


    // ADMIN ve COMPANY_OWNER için şirket silme yetkisi
    @DeleteMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('company:delete')")
    public SuccessResponse<Void> delete(@PathVariable @Positive Long companyId){
        companyService.delete(companyId);
        return SuccessResponse.success();
    }

    // ADMIN ve COMPANY_OWNER için şirket detaylarını görme yetkisi
    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('company:detail')")
    public SuccessResponse<CompanyResponse> findById(@PathVariable Long companyId){
        Company company = companyService.findCompanyById(companyId);
        CompanyResponse companyResponse = companyToResponseMapper.map(company);
        return SuccessResponse.success(companyResponse);
    }

    // ADMIN ve COMPANY_OWNER için şirket listesine erişim yetkisi
    @GetMapping("/companies")
    @PreAuthorize("hasAnyAuthority('company:list')")
    public SuccessResponse<List<CompanyResponse>> findAll(){
        List<Company> companyList = companyService.findAll();
        List<CompanyResponse> companyResponse = companyList.stream().map(companyToResponseMapper::map).toList();
        return SuccessResponse.success(companyResponse);
    }

    // ADMIN ve COMPANY_OWNER için şirket güncelleme yetkisi
    @PutMapping("/{companyId}")
    @PreAuthorize("hasAnyAuthority('company:update')")
    public SuccessResponse<Void> update(@RequestBody CompanyUpdateRequest request, @PathVariable Long companyId){
        companyService.update(request, companyId);
        return SuccessResponse.success();
    }


    //todo compnay:detail dan daha spesik bir isim vermek gerekir.
    //sahip oldugu şirketleri dönmesi için yazdım.
    @GetMapping("/me/companies")
    @PreAuthorize("hasAnyAuthority('company:detail')")
    public SuccessResponse<List<CompanySummaryResponse>> findCompaniesByToken(@RequestHeader("Authorization") String token) {
        List<Company> companyList = companyService.findCompaniesByToken(token);

        List<CompanySummaryResponse> companyResponses = companyList.stream()
                .map(company -> {
                    CompanySummaryResponse response = CompanySummaryResponse.builder()
                            .id(company.getId())
                            .name(company.getName())
                            .build();
                    return response;
                })
                .collect(Collectors.toList());

        return SuccessResponse.success(companyResponses);
    }


}
