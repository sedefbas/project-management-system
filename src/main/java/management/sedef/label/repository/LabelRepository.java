package management.sedef.label.repository;

import management.sedef.label.model.entity.LabelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<LabelEntity, Long> {

    @Query(value = """
        SELECT l
        FROM LabelEntity l
        WHERE l.isDefault = true
        UNION
        SELECT l
        FROM CompanyLabelEntity cl
        JOIN cl.label l
        WHERE cl.company.id = :companyId
    """)
    Optional<List<LabelEntity>> findLabelsByCompanyId(@Param("companyId") Long companyId);

}
