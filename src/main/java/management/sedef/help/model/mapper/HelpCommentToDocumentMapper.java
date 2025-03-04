package management.sedef.help.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.help.model.HelpComment;
import management.sedef.help.model.document.HelpCommentDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HelpCommentToDocumentMapper extends BaseMapper<HelpComment, HelpCommentDocument> {
    static HelpCommentToDocumentMapper initialize(){
        return Mappers.getMapper(HelpCommentToDocumentMapper.class);
    }
}
