package management.sedef.label.repository;

import management.sedef.label.model.entity.CompanyLabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CompanyLabelRepository extends JpaRepository<CompanyLabelEntity,Long> {

    Optional<CompanyLabelEntity> findByCompanyIdAndLabelId(Long companyId, Long labelId);

}
