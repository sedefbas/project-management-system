package management.sedef.company.repository;

import management.sedef.company.model.entity.SubGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubGroupRepository extends JpaRepository<SubGroupEntity,Long> {
    List<SubGroupEntity> findByGroup_Id(Long groupId);
}
