package management.sedef.issue.model.mapper.issueHistoryMapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueHistory;
import management.sedef.issue.model.mapper.issue.IssueIdToDomainMapper;
import management.sedef.issue.model.request.IssueHistoryRequest;
import management.sedef.user.model.mapper.UserIdToDomainMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IssueIdToDomainMapper.class, UserIdToDomainMapper.class})
public interface IssueHistoryRequestToDomainMapper extends BaseMapper<IssueHistoryRequest, IssueHistory> {

    @Override
    @Mapping(target = "issue", source = "issueId") // IssueIdToDomainMapper kullanılacak
    @Mapping(target = "modifiedBy", source = "modifiedById") // UserIdToDomainMapper kullanılacak
    @Mapping(target = "modifiedAt", expression = "java(java.time.Instant.now())") // Şu anki zamanı al
    IssueHistory map(IssueHistoryRequest request);
}
