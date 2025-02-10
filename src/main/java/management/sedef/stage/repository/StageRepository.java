package management.sedef.stage.repository;

import management.sedef.stage.model.entity.StageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<StageEntity,Long> {
}
