package management.sedef.subscription.port;

import management.sedef.subscription.model.Subscription;

public interface SubscriptionDeletePort {

    void delete(Subscription subscription);
}
