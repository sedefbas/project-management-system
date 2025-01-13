package management.sedef.user.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.user.model.UserVerification;
import management.sedef.user.model.entity.UserVerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserVerificationToEntityMapper extends BaseMapper<UserVerification, UserVerificationEntity> {

    static UserVerificationToEntityMapper initialize() {
        return Mappers.getMapper(UserVerificationToEntityMapper.class);
    }
}