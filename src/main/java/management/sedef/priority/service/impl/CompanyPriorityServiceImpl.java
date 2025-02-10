package management.sedef.priority.service.impl;


import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Company;
import management.sedef.company.service.CompanyService;
import management.sedef.priority.model.CompanyPriority;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.mapper.companyPriority.CompanyPriorityRequestToDomainMapper;
import management.sedef.priority.model.request.CompanyPriorityRequest;
import management.sedef.priority.port.companypriority.CompanyPriorityDeletePort;
import management.sedef.priority.port.companypriority.CompanyPriorityReadPort;
import management.sedef.priority.port.companypriority.CompanyPrioritySavePort;
import management.sedef.priority.service.CompanyPriorityService;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CompanyPriorityServiceImpl implements CompanyPriorityService {

    private final CompanyPriorityDeletePort deletePort;
    private final CompanyPrioritySavePort savePort;
    private final CompanyPriorityReadPort readPort;
    private final CompanyService companyService;
    private final CompanyPriorityRequestToDomainMapper companyPriorityRequestToDomainMapper = CompanyPriorityRequestToDomainMapper.initialize();


    @Override
    public void addPriorityToCompany(Long companyId, CompanyPriorityRequest request) {
        Company company = companyService.findCompanyById(companyId);
        Priority priority = companyPriorityRequestToDomainMapper.map(request);
        priority.setIsDefault(false);

        CompanyPriority companyPriority = CompanyPriority.builder()
                .company(company)
                .priority(priority)
                .build();
        savePort.save(companyPriority);
    }

    @Override
    public void removePriorityFromCompany(Long companyId, Long priorityId) {
        CompanyPriority companyPriority = readPort.findByCompanyIdAndPriorityId(companyId, priorityId);
        deletePort.delete(companyPriority);
    }
}
