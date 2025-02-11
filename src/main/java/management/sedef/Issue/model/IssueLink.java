package management.sedef.issue.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import management.sedef.issue.model.enums.IssueLinkType;


@Getter
@Setter
@Builder
public class IssueLink {

    private Long id;

    private Issue issue;

    private Issue linkedIssue;

    private IssueLinkType linkType;
}
