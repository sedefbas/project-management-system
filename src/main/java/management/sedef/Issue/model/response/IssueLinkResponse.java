package management.sedef.issue.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import management.sedef.issue.model.enums.IssueLinkType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueLinkResponse {
    private Long id;
    private IssueSummaryResponse issue;
    private IssueSummaryResponse linkedIssue;
    private IssueLinkType linkType;
}
