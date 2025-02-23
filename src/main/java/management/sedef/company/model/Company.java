package management.sedef.company.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.company.model.enums.CompanyStatus;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.user.model.User;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Company {

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

    private List<User> owners;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;
}
