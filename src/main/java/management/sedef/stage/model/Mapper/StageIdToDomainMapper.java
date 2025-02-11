package management.sedef.stage.model.Mapper;


import management.sedef.stage.model.Stage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StageIdToDomainMapper {
    Stage toDomain(Long id);
}
