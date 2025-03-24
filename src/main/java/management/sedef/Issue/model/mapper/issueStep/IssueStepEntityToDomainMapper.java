package management.sedef.issue.model.mapper.issueStep;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.entity.IssueStepEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueStepEntityToDomainMapper extends BaseMapper<IssueStepEntity, IssueStep> {
    static IssueStepEntityToDomainMapper initialize(){
        return Mappers.getMapper(IssueStepEntityToDomainMapper.class);
    }
}
