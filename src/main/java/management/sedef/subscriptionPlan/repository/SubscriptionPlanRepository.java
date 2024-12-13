package management.sedef.subscriptionPlan.repository;


import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlanEntity,Long> {

    Optional<SubscriptionPlanEntity> findByStatus(SubscriptionPlanStatus status);
}

