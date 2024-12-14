package management.sedef.auth.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.TokenNotValidException;
import management.sedef.auth.model.InvalidToken;
import management.sedef.auth.port.InvalidTokenReadPort;
import management.sedef.auth.port.InvalidTokenSavePort;
import management.sedef.auth.service.InvalidTokenService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class InvalidTokenServiceImpl implements InvalidTokenService {

    private final InvalidTokenReadPort invalidTokenReadPort;
    private final InvalidTokenSavePort invalidTokenSavePort;

    public void checkForInvalidityOfToken(final String tokenId) {

        final boolean isTokenInvalid = invalidTokenReadPort.existsByTokenId(tokenId);

        if (isTokenInvalid) {
            throw new TokenNotValidException();
        }
    }

    public void saveAll(List<String> invalidTokenIds) {

        final List<InvalidToken> invalidTokens = invalidTokenIds.stream()
                .map(invalidTokenId -> InvalidToken.builder().tokenId(invalidTokenId).build())
                .collect(Collectors.toList());

        invalidTokenSavePort.saveAll(invalidTokens);
    }

}