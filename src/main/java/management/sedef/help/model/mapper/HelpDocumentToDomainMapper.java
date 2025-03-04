package management.sedef.help.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.help.model.Help;
import management.sedef.help.model.document.HelpDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface HelpDocumentToDomainMapper extends BaseMapper<HelpDocument, Help> {
    static HelpDocumentToDomainMapper initialize(){
        return Mappers.getMapper(HelpDocumentToDomainMapper.class);
    }
}
