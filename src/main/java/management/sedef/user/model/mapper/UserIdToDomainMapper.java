package management.sedef.user.model.mapper;



import management.sedef.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserIdToDomainMapper {

        @Mapping(target = "id", source = "id")
        User toDomain(Long id);

}
