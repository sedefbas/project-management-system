package management.sedef.user.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserToUserEntityMapper extends BaseMapper<User, UserEntity> {

    static UserToUserEntityMapper initialize() {
        return Mappers.getMapper(UserToUserEntityMapper.class);
    }
}
