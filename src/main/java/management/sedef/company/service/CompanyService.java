package management.sedef.company.service;

import management.sedef.common.model.request.TokenRequest;
import management.sedef.company.model.Company;
import management.sedef.company.model.request.CompanyRequest;
import management.sedef.company.model.request.CompanyUpdateRequest;

import java.util.List;

public interface CompanyService {

    Company findCompanyById(Long id);

    List<Company> findAll();

    void create(CompanyRequest request);

    void delete(Long id);

    void update(CompanyUpdateRequest request);

    List<Company> findCompaniesByToken(TokenRequest request);
}
