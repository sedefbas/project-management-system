package management.sedef.company.model.mapper.companymapper;

import management.sedef.company.model.Address;
import management.sedef.company.model.Company;
import management.sedef.company.model.request.CompanyRequest;
import management.sedef.company.service.AddressService;
import management.sedef.subscriptionPlan.service.SubscriptionPlanService;
import management.sedef.user.port.UserReadPort;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CompanyRequestToDomainMapperImpl {

    private final SubscriptionPlanService subscriptionPlanService;
    private final UserReadPort userReadPort;
    private final AddressService addressService;

    public CompanyRequestToDomainMapperImpl(SubscriptionPlanService subscriptionPlanService,
                                            UserReadPort userReadPort,
                                            AddressService addressService) {
        this.subscriptionPlanService = subscriptionPlanService;
        this.userReadPort = userReadPort;
        this.addressService = addressService;
    }

    public Company map(CompanyRequest request) {
        return Company.builder()
                .name(request.getName())
                .description(request.getDescription())
                .taxNumber(request.getTaxNumber())
                .subscriptionPlan(subscriptionPlanService.findByStatus(request.getSubscriptionPlanStatus()))
                .email(request.getEmail())
                .website(request.getWebsite())
                .phoneNumber(request.getPhoneNumber())
                .owners(
                        request.getOwnerIds().stream()
                                .map(userId -> userReadPort.findById(userId)
                                        .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId)))
                                .collect(Collectors.toList())
                )
                .address(mapAddressById(request.getAddressId()))
                .status(request.getStatus())
                .build();
    }

    // Address mapping helper method by ID
    private Address mapAddressById(Long addressId) {
        if (addressId == null) return null;
        return addressService.findById(addressId);
    }

}


