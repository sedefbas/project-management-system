package management.sedef.issue.model.response;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class IssueStepResponse {

    private Long id;

    private Long issueId;

    private int stepNumber;

    private String description;

    private  Boolean isDone;

    private Instant createdAt;
}
