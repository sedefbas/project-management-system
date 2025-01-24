package management.sedef.company.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.common.model.request.TokenRequest;
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
    private final CompanyToResponseMapper companyToResponseMapper = CompanyToResponseMapper.initialize();

    // ADMIN ve COMPANY_OWNER için şirket oluşturma yetkisi
    @PostMapping()
    @PreAuthorize("hasAnyAuthority('company:create')")
    public SuccessResponse<Void> create(@RequestBody CompanyRequest request){
        companyService.create(request);
        return SuccessResponse.success();
    }

    // ADMIN ve COMPANY_OWNER için şirket silme yetkisi
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('company:delete')")
    public SuccessResponse<Void> delete(@PathVariable @Positive Long id){
        companyService.delete(id);
        return SuccessResponse.success();
    }

    // ADMIN ve COMPANY_OWNER için şirket detaylarını görme yetkisi
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('company:detail')")
    public SuccessResponse<CompanyResponse> findById(@PathVariable Long id){
        Company company = companyService.findCompanyById(id);
        CompanyResponse companyResponse = companyToResponseMapper.map(company);
        return SuccessResponse.success(companyResponse);
    }

    // ADMIN ve COMPANY_OWNER için şirket listesine erişim yetkisi
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('company:list')")
    public SuccessResponse<List<CompanyResponse>> findAll(){
        List<Company> companyList = companyService.findAll();
        List<CompanyResponse> companyResponse = companyList.stream().map(companyToResponseMapper::map).toList();
        return SuccessResponse.success(companyResponse);
    }

    // ADMIN ve COMPANY_OWNER için şirket güncelleme yetkisi
    @PutMapping()
    @PreAuthorize("hasAnyAuthority('company:update')")
    public SuccessResponse<Void> update(@RequestBody CompanyUpdateRequest request){
        companyService.update(request);
        return SuccessResponse.success();
    }

    //sahip oldugu şirketleri dönmesi için yazdım.
    @GetMapping("/summary")
    @PreAuthorize("hasAnyAuthority('company:detail')")
    public SuccessResponse<List<CompanySummaryResponse>> findCompaniesByToken(@RequestBody TokenRequest request) {
        List<Company> companyList = companyService.findCompaniesByToken(request);

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
