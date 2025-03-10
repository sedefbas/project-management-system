package management.sedef.stage.model.Mapper;

import management.sedef.stage.model.Stage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageToIdMapper {
    default Long toId(Stage stage) {
        return (stage != null) ? stage.getId() : null;
    }
}
