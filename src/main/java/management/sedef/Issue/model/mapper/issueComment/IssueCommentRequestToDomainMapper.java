package management.sedef.issue.model.mapper.issueComment;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.mapper.issue.IssueIdToDomainMapper;
import management.sedef.issue.model.request.IssueCommentRequest;
import management.sedef.user.model.mapper.UserIdToDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserIdToDomainMapper.class, IssueIdToDomainMapper.class})
public interface IssueCommentRequestToDomainMapper  extends BaseMapper<IssueCommentRequest, IssueComment> {

    @Mapping(source = "issueId", target = "issue")
    IssueComment map(IssueCommentRequest request);
}
