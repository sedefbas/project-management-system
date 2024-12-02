package management.sedef.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.user.model.enums.UserVerificationStatus;
import management.sedef.user.model.enums.UserVerificationType;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "user_verification",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "type"})  // user_id ve type kombinasyonunu benzersiz yapar
)
public class UserVerificationEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserVerificationStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserVerificationType type;

}
