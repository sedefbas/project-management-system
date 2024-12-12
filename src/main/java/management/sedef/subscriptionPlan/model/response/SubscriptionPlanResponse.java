package management.sedef.subscriptionPlan.model.response;

import lombok.Getter;
import lombok.Setter;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;

import java.math.BigDecimal;

@Getter
@Setter
public class SubscriptionPlanResponse {
    private Long id;
    private SubscriptionPlanStatus subscriptionPlan;
    private String description;
    private Integer maxProjects;
    private Integer maxTasks;
    private Integer maxUsers;
    private BigDecimal price;
    private String features;
}
