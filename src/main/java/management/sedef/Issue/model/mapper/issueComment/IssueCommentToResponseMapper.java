package management.sedef.issue.model.mapper.issueComment;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.response.IssueCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface IssueCommentToResponseMapper extends BaseMapper<IssueComment, IssueCommentResponse> {

    @Override
    @Mapping(source = "author", target = "user")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    IssueCommentResponse map(IssueComment issueComment);

}
