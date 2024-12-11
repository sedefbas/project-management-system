package management.sedef.subscription.model.response;

import lombok.Getter;
import lombok.Setter;
import management.sedef.subscription.model.enums.SubscriptionPlan;

import java.math.BigDecimal;

@Getter
@Setter
public class SubscriptionResponse {
    private Long id;
    private SubscriptionPlan subscriptionPlan;
    private String description;
    private Integer maxProjects;
    private Integer maxTasks;
    private Integer maxUsers;
    private BigDecimal price;
    private String features;
}
