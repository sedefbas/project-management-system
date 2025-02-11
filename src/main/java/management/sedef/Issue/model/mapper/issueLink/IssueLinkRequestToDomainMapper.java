package management.sedef.issue.model.mapper.issueLink;

import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.mapper.issue.IssueIdToDomainMapper;
import management.sedef.issue.model.request.IssueLinkRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {IssueIdToDomainMapper.class})
public interface IssueLinkRequestToDomainMapper {

    @Mapping(target = "issue", source = "issueId")
    @Mapping(target = "linkedIssue", source = "linkedIssueId")
    @Mapping(target = "linkType", source = "issueLinkType")
    IssueLink toDomain(IssueLinkRequest request);
}
