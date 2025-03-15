package management.sedef.issue.model.mapper.issueHistoryMapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueHistory;
import management.sedef.issue.model.entity.IssueHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface IssueHistoryToEntityMapper  extends BaseMapper<IssueHistory, IssueHistoryEntity> {
    static IssueHistoryToEntityMapper initialize(){
        return Mappers.getMapper(IssueHistoryToEntityMapper.class);
    }
}

