package management.sedef.subscriptionPlan.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subscription_plan")
public class SubscriptionPlanEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private SubscriptionPlanStatus status;

    @Column(name = "description")
    private String description;

    @Column(name = "max_proje")
    private Integer maxProjects;

    @Column(name = "max_task")// Sınırsız olabilmesi için NULL
    private Integer maxTasks;

    @Column(name = "max_user")// Sınırsız olabilmesi için NULL
    private Integer maxUsers;

    @Column(name = "price")// Sınırsız olabilmesi için NULL
    private BigDecimal price;

    @Column(name = "features")// Ücretli planlar için fiyat bilgisi
    private String features;

}
