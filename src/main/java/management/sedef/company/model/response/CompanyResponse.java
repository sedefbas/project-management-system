package management.sedef.company.model.response;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Address;
import management.sedef.company.model.entity.AddressEntity;
import management.sedef.company.model.enums.CompanyStatus;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;

import java.util.List;

@Getter
@Setter
public class CompanyResponse {
    private Long id;
    private String name;
    private String description;
    private Long taxNumber;
    private Address address;
    private Long phoneNumber;
    private String email;
    private String website;
    private CompanyStatus status;
    private SubscriptionPlanStatus subscriptionPlan;
    private List<Long> owners_id;
}
