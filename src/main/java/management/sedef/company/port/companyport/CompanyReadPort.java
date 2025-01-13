package management.sedef.company.port.companyport;

import management.sedef.company.model.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyReadPort {
    Optional<Company> findById(Long id);
    List<Company> findAll();
    List<Company>findCompaniesByUserId(Long userId);
}
