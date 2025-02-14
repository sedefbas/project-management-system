package management.sedef.user.model.mapper;

import management.sedef.user.model.User;
import management.sedef.user.model.response.UserSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserToUserSummaryMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    UserSummaryResponse map(User user);
}
