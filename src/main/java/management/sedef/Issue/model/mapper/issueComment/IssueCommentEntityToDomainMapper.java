package management.sedef.issue.model.mapper.issueComment;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueComment;
import management.sedef.issue.model.entity.IssueCommentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueCommentEntityToDomainMapper extends BaseMapper<IssueCommentEntity, IssueComment> {
    static IssueCommentEntityToDomainMapper initialize(){
        return Mappers.getMapper(IssueCommentEntityToDomainMapper.class);
    }
}
