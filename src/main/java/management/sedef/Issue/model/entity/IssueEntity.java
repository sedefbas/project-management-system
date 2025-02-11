package management.sedef.issue.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import management.sedef.Label.model.entity.LabelEntity;
import management.sedef.priority.model.entity.PriorityEntity;
import management.sedef.project.model.entity.ProjectEntity;
import management.sedef.stage.model.entity.StageEntity;
import java.util.Date;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "issues")
public class IssueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String explanation;

    private Date startDate;

    private Date deadLineDate;

    @ManyToOne
    private StageEntity stage;

    @ManyToOne
    private ProjectEntity project;

    @ManyToOne
    private PriorityEntity priority;

    @ManyToOne
    private LabelEntity label;

}
