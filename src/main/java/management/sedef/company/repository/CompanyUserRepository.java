package management.sedef.company.repository;


import management.sedef.company.model.entity.CompanyUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CompanyUserRepository extends JpaRepository<CompanyUserEntity,Long> {

    @Query("SELECT c FROM CompanyUserEntity c WHERE c.user.id = :userId")
    Optional<CompanyUserEntity> findByUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM CompanyUserEntity c WHERE c.company.id = :companyId")
    List<CompanyUserEntity> findByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT c FROM CompanyUserEntity c WHERE c.company.id = :companyId AND c.user.id = :userId")
    Optional<CompanyUserEntity> findByCompanyIdAndUserId(@Param("companyId") Long companyId, @Param("userId") Long userId);

    boolean existsByUserId(Long userId);

}
