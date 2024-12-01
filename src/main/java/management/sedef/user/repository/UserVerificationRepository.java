package management.sedef.user.repository;

import management.sedef.user.model.entity.UserVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVerificationRepository extends JpaRepository<UserVerificationEntity,Long> {


}
