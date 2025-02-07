package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.UserNotFoundByEmailException;
import management.sedef.auth.service.TokenService;
import management.sedef.company.model.Company;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.request.CompanyUserRequest;
import management.sedef.company.port.companyUserPort.CompanyUserDeletePort;
import management.sedef.company.port.companyUserPort.CompanyUserReadPort;
import management.sedef.company.port.companyUserPort.CompanyUserSavePort;
import management.sedef.company.service.CompanyService;
import management.sedef.company.service.CompanyUserService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.service.UserEmailService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyUserServiceImpl implements CompanyUserService {

    private  final CompanyUserReadPort companyUserReadPort;
    private final CompanyUserSavePort companyUserSavePort;
    private final CompanyUserDeletePort companyUserDeletePort;
    private final TokenService tokenService;
    private final UserReadPort userReadPort;
    private final CompanyService companyService;
    private final UserEmailService userEmailService;




    @Override
    public CompanyUser findByToken(String token) {
        String jwt = token.replace("Bearer ", "");
        Long userId = tokenService.getUserIdFromToken(jwt) ;
        return findByUserId(userId);
    }

    public CompanyUser findByUserId(Long userId){
        return companyUserReadPort.findByUserId(userId);
    }

    @Override
    public List<CompanyUser> findByUsersCompanyId(Long companyId) {
        return companyUserReadPort.findByCompanyId(companyId).stream().toList();
    }



    @Override
    public void addUserToCompany(CompanyUserRequest companyUserRequest,Long companyId) {
         User user = userReadPort.findByEmail(companyUserRequest.getUserEmail()).orElseThrow(() -> new UserNotFoundByEmailException(companyUserRequest.getUserEmail()));
         Company company = companyService.findCompanyById(companyId);
         CompanyUser companyUser = new CompanyUser();
         companyUser.setUser(user);
         companyUser.setStartDate(companyUserRequest.getStartDate());
         companyUser.setCompany(company);
         companyUserSavePort.save(companyUser);
    }

    @Override
    public void delete( Long companyId,Long userId ) {
       CompanyUser companyUser =  companyUserReadPort.findByCompanyIdAndUserId(companyId,userId);
       companyUserDeletePort.delete(companyUser);
    }


    @Override
    public String sendUserInvitationToCompany(String email, Long companyId) {
        Company company = companyService.findCompanyById(companyId);
        Optional<User> user = userReadPort.findByEmail(email);
        String userFullName = user.get().getFirstName() + user.get().getLastName();

        if (user.isEmpty()) {
            userEmailService.sendRegisterInvitation(email, companyId, company.getName());
            return "Kayıt linki gönderildi"; // Kayıt linki gönderildi
        } else {
            userEmailService.sendCompanyInvitation(email, companyId, company.getName(),userFullName);
            return "Davet linki gönderildi"; // Davet linki gönderildi
        }
    }

}
