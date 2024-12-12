package management.sedef.subscriptionPlan.port;

import management.sedef.subscriptionPlan.model.SubscriptionPlan;


import java.util.List;
import java.util.Optional;

public interface SubscriptionPlanReadPort {

    List<SubscriptionPlan> findAll();

    Optional<SubscriptionPlan> findById(Long id);

}
