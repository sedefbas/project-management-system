package management.sedef.company.service;

import management.sedef.common.model.request.TokenRequest;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.request.CompanyUserDeleteRequest;
import management.sedef.company.model.request.CompanyUserRequest;
import java.util.List;

public interface CompanyUserService {
    CompanyUser findByToken(TokenRequest request);
    CompanyUser findByUserId(Long userId);
    List<CompanyUser>  findByCompanyId(Long companyId);
    void create(CompanyUserRequest companyUserRequest);
    void delete(CompanyUserDeleteRequest userDeleteRequest);
}
