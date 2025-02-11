package management.sedef.issue.model.mapper.issue;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.response.IssueSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueToIssueSummaryResponse extends BaseMapper<Issue, IssueSummaryResponse> {
    static IssueToIssueSummaryResponse initialize(){
        return Mappers.getMapper(IssueToIssueSummaryResponse.class);
    }

}
