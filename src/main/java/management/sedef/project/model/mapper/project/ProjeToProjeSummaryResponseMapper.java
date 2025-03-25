package management.sedef.project.model.mapper.project;

import management.sedef.project.model.Project;
import management.sedef.project.model.response.ProjeSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface ProjeToProjeSummaryResponseMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "photo", source = "photo")
    ProjeSummaryResponse toProjeSummaryResponse(Project project);
}
