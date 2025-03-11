package management.sedef.label.model.mapper.label;

import management.sedef.label.model.Label;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LabelIdToDomainMapper {
    Label toDomain(Long id);
}
