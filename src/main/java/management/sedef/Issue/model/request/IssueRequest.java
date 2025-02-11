package management.sedef.issue.model.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@Builder
public class IssueRequest {
    private String name;
    private String explanation;
    private Date startDate;
    private Date deadLineDate;
    private Long stageId;
    private Long projectId;
    private Long priorityId;
    private Long labelId;
}
