package management.sedef.issue.model;


import lombok.*;
import management.sedef.Label.model.Label;
import management.sedef.priority.model.Priority;
import management.sedef.project.model.Project;
import management.sedef.stage.model.Stage;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Issue {

    private Long id;

    private String name;

    private String explanation;

    private Date startDate;

    private Date deadLineDate;

    private Stage stage;

    private Project project;

    private Priority priority;

    private Label label;
}
