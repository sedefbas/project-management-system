package management.sedef.issue.model.mapper.issueHistoryMapper;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueHistory;
import management.sedef.issue.model.entity.IssueHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueHistoryEntityToDomainMapper extends BaseMapper<IssueHistoryEntity, IssueHistory> {
    static IssueHistoryEntityToDomainMapper initialize(){
        return Mappers.getMapper(IssueHistoryEntityToDomainMapper.class);
    }
}
