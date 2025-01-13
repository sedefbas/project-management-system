package management.sedef.user.repository;

import management.sedef.user.model.entity.UserVerificationEntity;
import management.sedef.user.model.enums.UserVerificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserVerificationRepository extends JpaRepository<UserVerificationEntity,Long> {

    Optional<UserVerificationEntity> findByTypeAndId(UserVerificationType type, Long id);
}
