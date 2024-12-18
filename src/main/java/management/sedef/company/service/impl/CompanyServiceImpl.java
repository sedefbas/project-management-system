package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.company.exception.CompanyNotFoundException;
import management.sedef.company.model.Company;
import management.sedef.company.model.mapper.companymapper.CompanyRequestToDomainMapperImpl;
import management.sedef.company.model.request.CompanyRequest;
import management.sedef.company.port.companyport.CompanyDeletePort;
import management.sedef.company.port.companyport.CompanyReadPort;
import management.sedef.company.port.companyport.CompanySavePort;
import management.sedef.company.service.CompanyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanySavePort companySavePort;
    private final CompanyDeletePort companyDeletePort;
    private final CompanyReadPort companyReadPort;
    private final CompanyRequestToDomainMapperImpl companyRequestToDomainMapper;

    @Override
    public Company findById(Long id) {
        Company company =  companyReadPort.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException(id));
        return company;
    }

    @Override
    public List<Company> findAll() {
       return companyReadPort.findAll();
    }

    @Override
    public void create(CompanyRequest request) {
      Company company = companyRequestToDomainMapper.map(request);
      companySavePort.save(company);
    }

    @Override
    public void delete(Long id) {
        Company company = companyReadPort.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException(id));
        companyDeletePort.delete(company);
    }

    @Override
    public void update(Long id,CompanyRequest request) {
        Company company = companyReadPort.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException(id));
        companyRequestToDomainMapper.map(request);
    }

    @Override
    public List<Company> findCompaniesByUserId(Long userId) {
        return companyReadPort.findCompaniesByUserId(userId);
    }
}
