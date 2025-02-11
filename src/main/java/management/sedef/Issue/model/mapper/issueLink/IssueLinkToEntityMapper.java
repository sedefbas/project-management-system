package management.sedef.issue.model.mapper.issueLink;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.issue.model.IssueLink;
import management.sedef.issue.model.entity.IssueLinkEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IssueLinkToEntityMapper extends BaseMapper<IssueLink,IssueLinkEntity> {
    static IssueLinkToEntityMapper initialize(){
        return Mappers.getMapper(IssueLinkToEntityMapper.class);
    }

}
