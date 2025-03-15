package management.sedef.issue.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.user.model.entity.UserEntity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issues_histories")
public class IssueHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private IssueEntity issue;

    @ManyToOne
    @JoinColumn(name = "modified_by_id", nullable = false)
    private UserEntity modifiedBy;

    @Column(nullable = false, length = 1000)
    private String changeDescription;

    @Column(nullable = false)
    private Instant modifiedAt;

    @Column(nullable = false)
    private Instant expirationDate;
}