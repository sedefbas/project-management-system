package management.sedef.help.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.help.model.Help;
import management.sedef.help.model.document.HelpDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface HelpToDocumentMapper extends BaseMapper<Help, HelpDocument> {
    static HelpToDocumentMapper initialize(){
        return Mappers.getMapper(HelpToDocumentMapper.class);
    }
}
