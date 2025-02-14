package management.sedef.stage.repository;

import management.sedef.stage.model.entity.StageEntity;
import management.sedef.stage.model.enums.StageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;



public interface StageRepository extends JpaRepository<StageEntity,Long> {
    Optional<StageEntity> findByName(StageType name);
}
