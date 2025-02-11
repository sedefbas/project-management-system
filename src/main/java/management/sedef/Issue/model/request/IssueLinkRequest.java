package management.sedef.issue.model.request;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.issue.model.enums.IssueLinkType;

@Getter
@Setter
@Builder
public class IssueLinkRequest {
  private Long issueId;
  private Long linkedIssueId;
  private IssueLinkType issueLinkType;
}
