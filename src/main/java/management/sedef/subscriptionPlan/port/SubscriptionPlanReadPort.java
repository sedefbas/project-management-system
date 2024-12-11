package management.sedef.subscription.port;

import management.sedef.subscription.model.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionReadPort {

    List<Subscription> findAll();
    Optional<Subscription> findById(Long id);

}
