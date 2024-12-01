package management.sedef.auth.model.entity;

import jakarta.persistence.*;
import lombok.*;
import management.sedef.auth.model.enums.RoleName;
import management.sedef.auth.model.enums.RoleStatus;

import java.util.List;

//
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class RoleEntity  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private RoleName name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private RoleStatus status;

    //Bir rolün sahip olduğu izinleri belirten ilişki tablosu:
    @ManyToMany
    @JoinTable(
            name = "role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<PermissionEntity> permissions;

}
