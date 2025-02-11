package management.sedef.issue.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.sedef.issue.model.enums.IssueLinkType;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issue_links")
public class IssueLinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "issue_id", nullable = false)
    private IssueEntity issue; // Bağlantıyı oluşturan issue

    @ManyToOne
    @JoinColumn(name = "linked_issue_id", nullable = false)
    private IssueEntity linkedIssue; // Bağlantılı issue

    @Enumerated(EnumType.STRING)
    @Column(name = "link_type")
    private IssueLinkType linkType; // Bağlantı tipi (BLOCKS, DEPENDS_ON, RELATES_TO)
}
