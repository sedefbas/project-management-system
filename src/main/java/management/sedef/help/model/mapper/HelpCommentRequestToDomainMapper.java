package management.sedef.help.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.help.model.HelpComment;
import management.sedef.help.model.request.HelpCommentRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HelpCommentRequestToDomainMapper extends BaseMapper<HelpCommentRequest, HelpComment> {
    static HelpCommentRequestToDomainMapper initialize(){
        return Mappers.getMapper(HelpCommentRequestToDomainMapper.class);
    }
}
