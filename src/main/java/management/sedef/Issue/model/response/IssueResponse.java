package management.sedef.issue.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import management.sedef.company.model.response.GroupResponse;
import management.sedef.company.model.response.SubGroupResponse;
import management.sedef.label.model.response.LabelSummaryResponse;
import management.sedef.priority.model.response.PrioritySummaryResponse;
import management.sedef.stage.response.StageSummaryResponse;
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
    private StageSummaryResponse stage;
    private Long projectId;
    private PrioritySummaryResponse priority;
    private LabelSummaryResponse label;
    private GroupResponse group;
    private SubGroupResponse subGroup;
}
