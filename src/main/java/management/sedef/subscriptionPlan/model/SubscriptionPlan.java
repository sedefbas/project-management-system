package management.sedef.subscriptionPlan.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
public class SubscriptionPlan {

    private Long id;

    private SubscriptionPlanStatus status;

    private String description;

    private Integer maxProjects;

    private Integer maxTasks;

    private Integer maxUsers;

    private BigDecimal price;

    private String features;
}
