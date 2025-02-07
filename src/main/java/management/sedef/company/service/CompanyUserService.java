package management.sedef.company.service;

import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.request.CompanyUserRequest;
import management.sedef.user.model.User;

import java.util.List;

public interface CompanyUserService {
    CompanyUser findByToken(String token);
    List<CompanyUser> findByUsersCompanyId(Long companyId);
    void addUserToCompany(CompanyUserRequest companyUserRequest, Long companyId);
    void delete( Long companyId,Long userId );
    String sendUserInvitationToCompany(String email, Long companyId);
}
