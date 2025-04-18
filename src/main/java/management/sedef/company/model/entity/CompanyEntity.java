package management.sedef.company.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.company.model.enums.CompanyStatus;

import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;
import management.sedef.user.model.entity.UserEntity;

import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class CompanyEntity  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "logo", columnDefinition = "TEXT")
    private String logo;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number", unique = false)
    private Long phoneNumber;

    @Column(name = "email", unique = false)  // Benzersiz email
    private String email;

    @Column(name = "website", unique = false)  // Benzersiz website
    private String website;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CompanyStatus status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "subscription_plan_id")
    private SubscriptionPlanEntity subscriptionPlan;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "company_owners",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> owners;


}

//    private List<Team> teams; // Teams within the company
//    private List<User> users; // Employees in the company
//    private List<Project> projects; // Projects associated with the company
