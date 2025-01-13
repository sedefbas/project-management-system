package management.sedef.subscriptionPlan.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum SubscriptionPlanStatus {
    FREE,
    PREMIUM,
    ENTERPRISE,
    STANDART,
    BASIC;
}
