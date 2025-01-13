package management.sedef.subscription.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.company.model.entity.CompanyEntity;
import management.sedef.subscription.model.enums.SubscriptionStatus;
import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription")
public class SubscriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

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
