package management.sedef.subscription.repository;

import management.sedef.subscription.model.entity.SubscriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<SubscriptionEntity,Long> {
}
