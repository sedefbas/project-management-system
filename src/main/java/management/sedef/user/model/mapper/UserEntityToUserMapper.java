package management.sedef.user.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserEntityToUserMapper extends BaseMapper<UserEntity, User> {

    static UserEntityToUserMapper initialize() {
        return Mappers.getMapper(UserEntityToUserMapper.class);
    }
}
