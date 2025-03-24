package management.sedef.company.model.mapper.subgroupmapper;


import management.sedef.company.model.SubGroup;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubgroupIdToDomainMapper {
    SubGroup toDomain(Long id);
}
