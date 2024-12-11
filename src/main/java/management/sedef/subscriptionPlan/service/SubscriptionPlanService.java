package management.sedef.subscription.service;

import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.request.SubscriptionRequest;

import java.util.List;

public interface SubscriptionService {

    Subscription findById(Long id);

    List<Subscription> findAll();

    void create(SubscriptionRequest request);

    void delete(Long id);

    void update(Long id, SubscriptionRequest subscriptionRequest);

}