package management.sedef.subscription.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.company.model.Company;
import management.sedef.subscription.model.enums.SubscriptionStatus;
import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;

import java.time.LocalDate;

@Getter
@Setter
@SuperBuilder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "subscription_plan_id")
    private SubscriptionPlanEntity subscriptionPlan;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "final_date")
    private LocalDate finalDate;

    @Column(name = "status")
    private SubscriptionStatus status;
}
