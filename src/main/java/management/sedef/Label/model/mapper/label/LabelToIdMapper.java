package management.sedef.Label.model.mapper.label;

import management.sedef.Label.model.Label;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelToIdMapper {
    default Long toId(Label label) {
        return (label != null) ? label.getId() : null;
    }
}
