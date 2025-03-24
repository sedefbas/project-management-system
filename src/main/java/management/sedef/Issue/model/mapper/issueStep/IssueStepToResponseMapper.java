package management.sedef.issue.model.mapper.issueStep;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.response.IssueStepResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IssueStepToResponseMapper extends BaseMapper<IssueStep, IssueStepResponse> {

    @Mapping(source = "issue.id", target = "issueId")
    IssueStepResponse map(IssueStep issueStep);
}

