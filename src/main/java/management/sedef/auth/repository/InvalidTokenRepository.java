package management.sedef.auth.repository;

import management.sedef.auth.model.entity.InvalidTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;



public interface InvalidTokenRepository extends JpaRepository<InvalidTokenEntity, Long> {

    boolean existsByTokenId(String tokenId);

    Optional<InvalidTokenEntity> findByTokenId(String tokenId);

    void deleteAllByCreatedAtBefore(LocalDateTime createdAt);
}
