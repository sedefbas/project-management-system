package management.sedef.auth.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.model.InvalidToken;
import management.sedef.auth.model.entity.InvalidTokenEntity;
import management.sedef.auth.model.mapper.InvalidTokenEntityToDomainMapper;
import management.sedef.auth.model.mapper.InvalidTokenToEntityMapper;
import management.sedef.auth.port.InvalidTokenDeletePort;
import management.sedef.auth.port.InvalidTokenReadPort;
import management.sedef.auth.port.InvalidTokenSavePort;
import management.sedef.auth.repository.InvalidTokenRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
class InvalidTokenAdapter implements InvalidTokenReadPort, InvalidTokenSavePort, InvalidTokenDeletePort {

    private final InvalidTokenRepository invalidTokenRepository;


    private final InvalidTokenToEntityMapper invalidTokenToEntityMapper = InvalidTokenToEntityMapper.initialize();
    private final InvalidTokenEntityToDomainMapper invalidTokenEntityToDomainMapper = InvalidTokenEntityToDomainMapper.initialize();

    @Override
    public boolean existsByTokenId(final String tokenId) {
        return invalidTokenRepository.existsByTokenId(tokenId);
    }

    @Override
    public Optional<InvalidToken> findByTokenId(final String tokenId) {
        final Optional<InvalidTokenEntity> invalidTokenEntity = invalidTokenRepository.findByTokenId(tokenId);
        return invalidTokenEntity.map(invalidTokenEntityToDomainMapper::map);
    }


    @Override
    @Transactional
    public void saveAll(final List<InvalidToken> invalidTokens) {
        final List<InvalidTokenEntity> invalidTokenEntities = invalidTokenToEntityMapper.map(invalidTokens);
        invalidTokenRepository.saveAll(invalidTokenEntities);
    }

    @Override
    @Transactional
    public void deleteAllByCreatedAtBefore(LocalDateTime expirationThreshold) {
        invalidTokenRepository.deleteAllByCreatedAtBefore(expirationThreshold);

    }
}
