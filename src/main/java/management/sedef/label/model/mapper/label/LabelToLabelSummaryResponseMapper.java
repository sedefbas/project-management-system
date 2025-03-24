package management.sedef.label.model.mapper.label;

import management.sedef.label.model.Label;
import management.sedef.label.model.entity.LabelEntity;
import management.sedef.label.model.response.LabelSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LabelToLabelSummaryResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    LabelSummaryResponse toLabelSummaryResponse(Label label);
}
