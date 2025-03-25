package management.sedef.company.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sub_bands")
public class SubGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 7)
    private String color;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "group_id", nullable = false)
    private GroupEntity group;

}
