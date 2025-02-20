package management.sedef.issue.model.mapper.issueAssignment;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.entity.IssueAssignmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueAssignmentEntityToDomainMapper extends BaseMapper<IssueAssignmentEntity, IssueAssignment> {
    static IssueAssignmentEntityToDomainMapper initialize(){
        return Mappers.getMapper(IssueAssignmentEntityToDomainMapper.class);
    }
}
