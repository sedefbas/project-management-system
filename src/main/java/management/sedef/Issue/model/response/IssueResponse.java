package management.sedef.issue.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.user.model.response.UserSummaryResponse;

import java.util.Date;

@Getter
@Setter
@Builder
public class IssueResponse {
    private Long id;
    private String name;
    private String explanation;
    private Date startDate;
    private Date deadLineDate;
    private UserSummaryResponse createdBy;
    private Long stageId;
    private Long projectId;
    private Long priorityId;
    private Long labelId;
}
