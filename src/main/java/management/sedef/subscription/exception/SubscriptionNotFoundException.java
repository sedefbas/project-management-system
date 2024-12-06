package management.sedef.subscription.exception;

import management.sedef.common.exception.AbstractNotFoundException;


public final class SubscriptionNotFoundException  extends AbstractNotFoundException {

    public SubscriptionNotFoundException(Long subscriptionID) {
        super("subscriptionID not found with id: " + subscriptionID);
    }

}
