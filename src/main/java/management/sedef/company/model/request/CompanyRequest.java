package management.sedef.company.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import management.sedef.company.model.enums.CompanyStatus;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;

import java.util.List;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequest {

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

    @NotEmpty
    private List<Long> ownerIds;

    private Long addressId;

    private CompanyStatus status;

}
