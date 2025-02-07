package management.sedef.company.port.companyUserPort;

import management.sedef.company.model.CompanyUser;

import java.util.List;

public interface CompanyUserReadPort {
    CompanyUser findByUserId(Long id);
    List<CompanyUser> findByCompanyId(Long id);
    CompanyUser findByCompanyIdAndUserId(Long companyId, Long userId);
}
