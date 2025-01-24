package management.sedef.company.repository;

import management.sedef.company.model.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyEntity,Long> {

    CompanyEntity findByIdAndOwnersIsNotNull(Long id);

    @Query("SELECT c FROM CompanyEntity c JOIN c.owners u WHERE u.id = :userId")
    List<CompanyEntity> findCompaniesByUserId(Long userId);


}
