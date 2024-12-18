package management.sedef.company.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Company;
import management.sedef.company.model.entity.CompanyEntity;
import management.sedef.company.model.mapper.companymapper.CompanyEntityToDomainMapper;
import management.sedef.company.model.mapper.companymapper.CompanyToEntityMapper;
import management.sedef.company.port.companyport.CompanyDeletePort;
import management.sedef.company.port.companyport.CompanyReadPort;
import management.sedef.company.port.companyport.CompanySavePort;
import management.sedef.company.repository.CompanyRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CompanyAdapter implements CompanyReadPort, CompanyDeletePort, CompanySavePort {

   private final CompanyRepository repository;
   private final CompanyEntityToDomainMapper companyEntityToDomainMapper = CompanyEntityToDomainMapper.initialize();
   private final CompanyToEntityMapper companyToEntityMapper = CompanyToEntityMapper.initialize();


    @Override
    public Optional<Company> findById(Long id) {
       return repository.findById(id)
               .map(companyEntityToDomainMapper::map);
    }

    @Override
    public List<Company> findAll() {
        return repository.findAll().stream().map(companyEntityToDomainMapper::map).toList();
    }

    @Override
    public List<Company> findCompaniesByUserId(Long userId) {
        return repository.findCompaniesByUserId(userId).stream().map(companyEntityToDomainMapper::map).toList();
    }

    @Override
    public void delete(Company company) {
        final CompanyEntity companyEntity = companyToEntityMapper.map(company);
        repository.delete(companyEntity);
    }

    @Override
    public Company save(Company company) {
        final CompanyEntity companyEntity = companyToEntityMapper.map(company);
        repository.save(companyEntity);
        return companyEntityToDomainMapper.map(companyEntity);
    }
}
