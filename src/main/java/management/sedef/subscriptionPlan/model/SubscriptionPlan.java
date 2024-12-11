package management.sedef.subscription.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.subscription.model.enums.SubscriptionPlan;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
public class Subscription {

    private Long id;

    private SubscriptionPlan subscriptionPlan;

    private String description;

    private Integer maxProjects;

    private Integer maxTasks;

    private Integer maxUsers;

    private BigDecimal price;

    private String features;
}
