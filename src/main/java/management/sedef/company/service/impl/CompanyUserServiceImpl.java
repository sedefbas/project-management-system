package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.UserNotFoundByEmailException;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.enums.TokenClaims;
import management.sedef.auth.service.TokenService;
import management.sedef.company.exception.UserAlreadyAssignedToCompanyException;
import management.sedef.company.model.Company;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.claims.CompanyUserClaims;
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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;

import java.time.LocalDate;
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


    

    public String generateInvitationLink(String email, Long companyId) {
        final Claims claims = this.generateClaims(email,companyId);
        Token token =  tokenService.generate(claims);
        return token.getAccessToken();
    }

    private Claims generateClaims( String email, Long companyId) {
        final ClaimsBuilder claimsBuilder = Jwts.claims();

        claimsBuilder.add(TokenClaims.USER_MAIL.getValue(), email);
        claimsBuilder.add(TokenClaims.COMPANY_ID.getValue(),companyId);
        return claimsBuilder.build();
    }


    
    @Override
    public String sendUserInvitationToCompany(String email, Long companyId) {
        Company company = companyService.findCompanyById(companyId);
        Optional<User> user = userReadPort.findByEmail(email);
        String userFullName = user.get().getFirstName() + user.get().getLastName();
        String token = generateInvitationLink(email,companyId);


        if (user.isEmpty()) {
            userEmailService.sendRegisterInvitation(email, companyId, company.getName(),token);
            return "Kayıt linki gönderildi"; // Kayıt linki gönderildi
        } else {
            userEmailService.sendCompanyInvitation(email, companyId, company.getName(),userFullName,token);
            return "Davet linki gönderildi"; // Davet linki gönderildi
        }
    }


    @Override
    public void addUserToCompany(String token) {
        // Token'dan gerekli bilgileri al
        CompanyUserClaims claims = tokenService.parseCompanyInvitationToken(token);
        String email = claims.getEmail();
        Long companyId = claims.getCompanyId();
        
        // Kullanıcı ve şirket bilgilerini al
        User user = userReadPort.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundByEmailException(email));
        Company company = companyService.findCompanyById(companyId);
        
        // Kullanıcının başka bir şirkete kayıtlı olup olmadığını kontrol et
        boolean exists = companyUserReadPort.existsByUserId(user.getId());
        if (exists) {
            throw new UserAlreadyAssignedToCompanyException("Bu kullanıcı zaten bir şirkete kayıtlı!");
        }
        
        // Yeni CompanyUser oluştur ve kaydet
        CompanyUser companyUser = new CompanyUser();
        companyUser.setUser(user);
        companyUser.setStartDate(LocalDate.now()); // Başlangıç tarihi olarak bugünü set et
        companyUser.setCompany(company);
        companyUserSavePort.save(companyUser);
    }


    @Override
    public CompanyUser findByToken(String token) {
        Long userId = tokenService.getUserIdFromToken(token) ;
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
    public void delete( Long companyId,Long userId ) {
       CompanyUser companyUser =  companyUserReadPort.findByCompanyIdAndUserId(companyId,userId);
       companyUserDeletePort.delete(companyUser);
    }



}
