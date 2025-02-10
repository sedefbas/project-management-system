package management.sedef.priority.repository;

import management.sedef.priority.model.entity.PriorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PriorityRepository extends JpaRepository<PriorityEntity, Long> {

    @Query(value = """
        SELECT p
        FROM PriorityEntity p
        WHERE p.isDefault = true
        UNION
        SELECT p
        FROM CompanyPriorityEntity cp
        JOIN cp.priority p
        WHERE cp.company.id = :companyId
    """)
    Optional<List<PriorityEntity>> findPrioritiesByCompanyId(@Param("companyId") Long companyId);

}
