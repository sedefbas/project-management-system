package management.sedef.auth.port;

import management.sedef.auth.model.InvalidToken;

import java.util.List;

public interface InvalidTokenSavePort {

    void saveAll(List<InvalidToken> invalidTokens);

}
