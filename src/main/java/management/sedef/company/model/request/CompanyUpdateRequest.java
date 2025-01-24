package management.sedef.company.model.request;


import lombok.*;
import management.sedef.company.model.enums.CompanyStatus;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateRequest {

    private Long companyId;

    private String name;

    private String description;

    private Long taxNumber;

    private Long phoneNumber;

    private String email;

    private String website;

    private AddressRequest address;

}
