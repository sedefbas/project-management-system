package management.sedef.auth.port;

public interface InvalidTokenReadPort {

    boolean existsByTokenId(String tokenId);

}

