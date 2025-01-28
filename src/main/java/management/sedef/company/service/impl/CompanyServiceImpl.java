package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.service.TokenService;
import management.sedef.company.exception.CompanyNotFoundException;
import management.sedef.company.model.Address;
import management.sedef.company.model.Company;
import management.sedef.company.model.enums.CompanyStatus;
import management.sedef.company.model.mapper.companymapper.CompanyRequestToDomainMapper;
import management.sedef.company.model.request.CompanyRequest;
import management.sedef.company.model.request.CompanyUpdateRequest;
import management.sedef.company.port.companyport.CompanyDeletePort;
import management.sedef.company.port.companyport.CompanyReadPort;
import management.sedef.company.port.companyport.CompanySavePort;
import management.sedef.company.service.AddressService;
import management.sedef.company.service.CompanyService;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;
import management.sedef.subscriptionPlan.service.SubscriptionPlanService;
import management.sedef.user.model.User;
import management.sedef.user.port.adapter.UserAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanySavePort companySavePort;
    private final CompanyDeletePort companyDeletePort;
    private final CompanyReadPort companyReadPort;
    private final AddressService addressService;
    private final TokenService tokenService;
    private final CompanyRequestToDomainMapper companyRequestToDomainMapper = CompanyRequestToDomainMapper.initialize();
    private final UserAdapter userAdapter;
    private final SubscriptionPlanService subscriptionPlanService;

    @Override
    public Company findCompanyById(Long id) {
        Company company =  companyReadPort.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException(id));
        return company;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Company> findAll() {
       return companyReadPort.findAll();
    }

    @Transactional
    @Override
    public void create(CompanyRequest request, String token) {
        String jwt = token.replace("Bearer ", ""); // "Bearer " kısmını kaldır
        Long userId = tokenService.getUserIdFromToken(jwt);
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.findByStatus(SubscriptionPlanStatus.FREE);

        Company company = companyRequestToDomainMapper.map(request);
        User user = userAdapter.findById(userId).get();
        company.setOwners(List.of(user));
        company.setStatus(CompanyStatus.ACTIVE);
        company.setSubscriptionPlan(subscriptionPlan);

        if (request.getAddress() != null) {
            Address address = addressService.create(request.getAddress());
            company.setAddress(address);
        }
        companySavePort.save(company);
    }

    @Override
    public void delete(Long id) {
        Company company = companyReadPort.findById(id)
                .orElseThrow(()-> new CompanyNotFoundException(id));
        companyDeletePort.delete(company);
    }

    @Override
    public void update(CompanyUpdateRequest request,Long companyId) {

        Company company = this.findCompanyById(companyId);

        company.setName(request.getName());
        company.setDescription(request.getDescription());
        company.setPhoneNumber(request.getPhoneNumber());
        company.setEmail(request.getEmail());
        company.setWebsite(request.getWebsite());

        if (request.getAddress() != null) {
            Address address = addressService.create(request.getAddress());
            company.setAddress(address);
        }

        companySavePort.save(company);

    }

    @Override
    public List<Company> findCompaniesByToken(String token) {
        String jwt = token.replace("Bearer ", ""); // "Bearer " kısmını kaldır
        Long userId = tokenService.getUserIdFromToken(jwt);
        return findCompaniesByUserId(userId);
    }


    public List<Company> findCompaniesByUserId(Long owner_id) {
        return companyReadPort.findCompaniesByUserId(owner_id);
    }
}
