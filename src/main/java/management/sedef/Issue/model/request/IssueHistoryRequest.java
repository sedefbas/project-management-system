package management.sedef.issue.model.request;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class IssueHistoryRequest {

    @NotNull(message = "Issue ID cannot be null")
    private Long issueId;

    @NotNull(message = "Modified By ID cannot be null")
    private Long modifiedById;

    private String changeDescription;
}
