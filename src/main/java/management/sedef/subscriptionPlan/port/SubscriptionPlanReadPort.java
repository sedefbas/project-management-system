package management.sedef.subscriptionPlan.port;

import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;


import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanReadPort {

    List<SubscriptionPlan> findAll();

    Optional<SubscriptionPlan> findById(Long id);

    Optional<SubscriptionPlan> findByStatus(SubscriptionPlanStatus status);

}
