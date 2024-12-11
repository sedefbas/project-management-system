package management.sedef.subscription.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SubscriptionRequest {

    private String subscriptionPlan;

    private String description;

    private Integer maxProjects;

    private Integer maxTasks;

    private Integer maxUsers;

    private BigDecimal price;

    private String features;
}
