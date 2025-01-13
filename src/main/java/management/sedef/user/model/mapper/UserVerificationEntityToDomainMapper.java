package management.sedef.user.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.user.model.UserVerification;
import management.sedef.user.model.entity.UserVerificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserVerificationEntityToDomainMapper extends BaseMapper<UserVerificationEntity, UserVerification> {

    static UserVerificationEntityToDomainMapper initialize() {
        return Mappers.getMapper(UserVerificationEntityToDomainMapper.class);
    }

}
