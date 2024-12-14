package management.sedef.auth.repository;

import management.sedef.auth.model.entity.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, Long> {

    boolean existsByTokenId(String tokenId);

}
