package management.sedef.company.service;

import management.sedef.company.model.CompanyUser;


import java.util.List;

public interface CompanyUserService {
    CompanyUser findByToken(String token);
    List<CompanyUser> findByUsersCompanyId(Long companyId);
    void delete( Long companyId,Long userId );
    String sendUserInvitationToCompany(String email, Long companyId);
    void addUserToCompany(String token);
}
