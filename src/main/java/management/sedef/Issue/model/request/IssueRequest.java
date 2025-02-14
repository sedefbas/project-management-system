package management.sedef.issue.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;


@Getter
@Setter
@Builder
public class IssueRequest {

    @NotNull
    private String name;
    @NotNull
    private String explanation;
    @NotNull
    private Date startDate;
    private Date deadLineDate;
    private Long stageId;
    @NotNull
    private Long projectId;
    private Long priorityId;
    private Long labelId;
}
