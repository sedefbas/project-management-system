package management.sedef.issue.model.mapper.issueAssignment;


import management.sedef.issue.model.IssueAssignment;
import management.sedef.issue.model.mapper.issue.IssueIdToDomainMapper;
import management.sedef.issue.model.request.IssueAssignmentRequest;
import management.sedef.user.model.mapper.UserIdToDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserIdToDomainMapper.class, IssueIdToDomainMapper.class})
public interface IssueAssignmentRequestToDomainMapper {

    @Mapping(target = "issue", source = "issueId")
    @Mapping(target = "assignedUser", source = "assignedUserId")
    @Mapping(target = "assignmentDate", expression = "java(new java.util.Date())")
    IssueAssignment map(IssueAssignmentRequest request);

}
