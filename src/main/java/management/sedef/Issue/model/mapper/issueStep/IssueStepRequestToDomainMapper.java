package management.sedef.issue.model.mapper.issueStep;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueStep;

import management.sedef.issue.model.request.IssueStepRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface IssueStepRequestToDomainMapper extends BaseMapper<IssueStepRequest, IssueStep> {

    static IssueStepRequestToDomainMapper initialize(){
        return Mappers.getMapper(IssueStepRequestToDomainMapper.class);
    }
}
