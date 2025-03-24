package management.sedef.company.model.mapper.groupmapper;


import management.sedef.company.model.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupIdToDomainMapper {
    Group toDomain(Long id);
}
