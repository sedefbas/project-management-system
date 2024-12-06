package management.sedef.subscription.port;

import management.sedef.subscription.model.Subscription;

public interface SubscriptionSavePort {

    Subscription save(Subscription subscription);

}
