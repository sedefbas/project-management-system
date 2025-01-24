package management.sedef.company.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

    private String token;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private Long taxNumber;

    @NotNull
    private Long phoneNumber;

    @NotNull
    private String email;

    private String website;

    @NotNull
    private SubscriptionPlanStatus subscriptionPlanStatus;

    private AddressRequest address;

}
