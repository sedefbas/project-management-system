package management.sedef.auth.port;

import management.sedef.auth.model.InvalidToken;

import java.util.Optional;

public interface InvalidTokenReadPort {

    boolean existsByTokenId(String tokenId);
    Optional<InvalidToken> findByTokenId(String tokenId);

}

