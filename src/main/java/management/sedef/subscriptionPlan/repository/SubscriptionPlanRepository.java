package management.sedef.subscriptionPlan.repository;


import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlanEntity,Long> {
}
