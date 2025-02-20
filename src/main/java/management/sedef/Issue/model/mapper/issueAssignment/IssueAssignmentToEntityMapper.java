package management.sedef.issue.model.mapper.issueAssignment;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.entity.IssueAssignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueAssignmentToEntityMapper extends BaseMapper<IssueAssignment, IssueAssignmentEntity> {
    static IssueAssignmentToEntityMapper initialize(){
        return Mappers.getMapper(IssueAssignmentToEntityMapper.class);
    }

}
