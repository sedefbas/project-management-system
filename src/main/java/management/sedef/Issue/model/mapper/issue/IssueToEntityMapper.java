package management.sedef.issue.model.mapper.issue;


import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.Issue;
import management.sedef.issue.model.entity.IssueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface IssueToEntityMapper extends BaseMapper<Issue, IssueEntity> {
    static IssueToEntityMapper initialize(){
        return Mappers.getMapper(IssueToEntityMapper.class);
    }
}
