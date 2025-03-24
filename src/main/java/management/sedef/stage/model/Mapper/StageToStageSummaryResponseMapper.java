package management.sedef.stage.model.Mapper;

import management.sedef.stage.model.Stage;
import management.sedef.stage.response.StageSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StageToStageSummaryResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    StageSummaryResponse toStageSummaryResponse(Stage stage);
}
