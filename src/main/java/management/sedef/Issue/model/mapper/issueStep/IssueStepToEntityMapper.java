package management.sedef.issue.model.mapper.issueStep;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueStep;
import management.sedef.issue.model.entity.IssueStepEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueStepToEntityMapper extends BaseMapper<IssueStep, IssueStepEntity> {
    static IssueStepToEntityMapper initialize(){
        return Mappers.getMapper(IssueStepToEntityMapper.class);
    }
}
