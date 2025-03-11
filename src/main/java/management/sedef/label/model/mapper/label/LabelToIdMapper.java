package management.sedef.label.model.mapper.label;

import management.sedef.label.model.Label;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelToIdMapper {
    default Long toId(Label label) {
        return (label != null) ? label.getId() : null;
    }
}
