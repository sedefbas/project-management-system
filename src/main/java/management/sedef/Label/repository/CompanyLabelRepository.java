package management.sedef.Label.repository;

import management.sedef.Label.model.entity.CompanyLabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CompanyLabelRepository extends JpaRepository<CompanyLabelEntity,Long> {

    Optional<CompanyLabelEntity> findByCompanyIdAndLabelId(Long companyId, Long labelId);

}
