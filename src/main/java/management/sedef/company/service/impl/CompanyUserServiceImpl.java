package management.sedef.company.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.service.TokenService;
import management.sedef.common.model.request.TokenRequest;
import management.sedef.company.exception.UserNotFoundException;
import management.sedef.company.model.Company;
import management.sedef.company.model.CompanyUser;
import management.sedef.company.model.mapper.companyusermapper.CompanyRequestToDomainMapper;
import management.sedef.company.model.request.CompanyUserDeleteRequest;
import management.sedef.company.model.request.CompanyUserRequest;
import management.sedef.company.port.companyUserPort.CompanyUserDeletePort;
import management.sedef.company.port.companyUserPort.CompanyUserReadPort;
import management.sedef.company.port.companyUserPort.CompanyUserSavePort;
import management.sedef.company.service.CompanyService;
import management.sedef.company.service.CompanyUserService;
import management.sedef.user.model.User;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.port.adapter.UserAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyUserServiceImpl implements CompanyUserService {

    private  final CompanyUserReadPort companyUserReadPort;
    private final CompanyUserSavePort companyUserSavePort;
    private final CompanyUserDeletePort companyUserDeletePort;
    private final TokenService tokenService;
    private final UserReadPort userReadPort;
    private final CompanyService companyService;



    @Override
    public CompanyUser findByToken(TokenRequest request) {
        Long userId = tokenService.getUserIdFromToken(request.getToken()) ;
        return findByUserId(userId);
    }

    public CompanyUser findByUserId(Long userId){
        return companyUserReadPort.findByUserId(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public List<CompanyUser> findByCompanyId(Long companyId) {
        return companyUserReadPort.findByCompanyId(companyId).stream().toList();
    }


    @Override
    public void create(CompanyUserRequest companyUserRequest) {
         User user = userReadPort.findByEmail(companyUserRequest.getUserEmail()).orElseThrow(() -> new UserNotFoundException());
         Company company = companyService.findCompanyById(companyUserRequest.getCompanyId());
         CompanyUser companyUser = new CompanyUser();
         companyUser.setUser(user);
         companyUser.setStartDate(LocalDate.now());
         companyUser.setCompany(company);
         companyUserSavePort.save(companyUser);
    }

    @Override
    public void delete( @Valid CompanyUserDeleteRequest userDeleteRequest) {
       CompanyUser companyUser =  companyUserReadPort.findByCompanyIdAndUserId(userDeleteRequest.getCompanyId(), userDeleteRequest.getUserId());
       companyUserDeletePort.delete(companyUser);
    }

}
