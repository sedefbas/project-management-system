package management.sedef.company.port.companyUserPort;

import management.sedef.company.model.CompanyUser;

import java.util.List;
import java.util.Optional;

public interface CompanyUserReadPort {
    Optional<CompanyUser> findByUserId(Long id);
    List<CompanyUser> findByCompanyId(Long id);
    CompanyUser findByCompanyIdAndUserId(Long companyId, Long userId);
}
