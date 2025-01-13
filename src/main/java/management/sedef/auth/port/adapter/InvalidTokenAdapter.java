package management.sedef.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.InvalidToken;
import management.sedef.auth.model.entity.InvalidTokenEntity;
import management.sedef.auth.model.mapper.InvalidTokenToEntityMapper;
import management.sedef.auth.port.InvalidTokenReadPort;
import management.sedef.auth.port.InvalidTokenSavePort;
import management.sedef.auth.repository.InvalidTokenRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class InvalidTokenAdapter implements InvalidTokenReadPort, InvalidTokenSavePort {

    private final InvalidTokenRepository invalidTokenRepository;


    private final InvalidTokenToEntityMapper invalidTokenToEntityMapper = InvalidTokenToEntityMapper.initialize();


    @Override
    public boolean existsByTokenId(final String tokenId) {
        return invalidTokenRepository.existsByTokenId(tokenId);
    }


    @Override
    public void saveAll(final List<InvalidToken> invalidTokens) {
        final List<InvalidTokenEntity> invalidTokenEntities = invalidTokenToEntityMapper.map(invalidTokens);
        invalidTokenRepository.saveAll(invalidTokenEntities);
    }

}
