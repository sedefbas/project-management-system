package management.sedef.company.service;

import management.sedef.company.model.Company;
import management.sedef.company.model.request.CompanyRequest;

import java.util.List;

public interface CompanyService {

    Company findById(Long id);

    List<Company> findAll();

    void create(CompanyRequest request);

    void delete(Long id);

    void update(Long id,CompanyRequest request);
}
