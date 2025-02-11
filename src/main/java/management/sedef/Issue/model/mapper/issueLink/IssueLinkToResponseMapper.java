package management.sedef.issue.model.mapper.issueLink;


import management.sedef.issue.model.Issue;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.response.IssueLinkResponse;
import management.sedef.issue.model.response.IssueSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface IssueLinkToResponseMapper {

    @Mapping(target = "issue", source = "issue", qualifiedByName = "toSummary")
    @Mapping(target = "linkedIssue", source = "linkedIssue", qualifiedByName = "toSummary")
    IssueLinkResponse map(IssueLink issueLink); // Entity yerine domain sınıfı kullanılıyor

    @Named("toSummary")
    default IssueSummaryResponse toSummary(Issue issue) {
        return new IssueSummaryResponse(issue.getId(), issue.getName());
    }

}
