package management.sedef.issue.model;

import jakarta.persistence.*;
import lombok.*;


import java.time.Instant;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IssueStep {

    @Id
    private Long id;

    private Issue issue;

    private int stepNumber;

    private String description;

    private  Boolean isDone;

    private Instant createdAt;

}
