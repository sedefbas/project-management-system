package management.sedef.auth.model.mapper;

import management.sedef.auth.model.InvalidToken;
import management.sedef.auth.model.entity.InvalidTokenEntity;
import management.sedef.common.model.entity.mapper.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InvalidTokenEntityToDomainMapper extends BaseMapper<InvalidTokenEntity, InvalidToken> {
    static InvalidTokenEntityToDomainMapper initialize() {
        return Mappers.getMapper(InvalidTokenEntityToDomainMapper.class);
    }
}
