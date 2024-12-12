package management.sedef.subscriptionPlan.service;

import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.request.SubscriptionPlanRequest;

import java.util.List;

public interface SubscriptionPlanService {

    SubscriptionPlan findById(Long id);

    List<SubscriptionPlan> findAll();

    void create(SubscriptionPlanRequest request);

    void delete(Long id);

    void update(Long id, SubscriptionPlanRequest subscriptionRequest);

}