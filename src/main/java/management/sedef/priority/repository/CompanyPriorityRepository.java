package management.sedef.priority.repository;

import management.sedef.Label.model.entity.CompanyLabelEntity;
import management.sedef.priority.model.entity.CompanyPriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyPriorityRepository extends JpaRepository<CompanyPriorityEntity,Long> {
    Optional<CompanyPriorityEntity> findByCompanyIdAndPriorityId(Long companyId, Long priority);

}
