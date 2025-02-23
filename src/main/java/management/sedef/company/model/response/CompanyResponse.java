package management.sedef.company.model.response;

import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.Address;
import management.sedef.company.model.enums.CompanyStatus;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;


import java.util.List;

@Getter
@Setter
public class CompanyResponse {
    private Long id;
    private String name;
    private String logo;
    private String description;
    private Address address;
    private Long phoneNumber;
    private String email;
    private String website;
    private CompanyStatus status;
    private SubscriptionPlan subscriptionPlan;
    private List<Long> owners_id;
}
