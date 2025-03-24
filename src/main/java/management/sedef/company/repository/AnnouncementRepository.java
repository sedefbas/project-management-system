package management.sedef.company.repository;

import management.sedef.company.model.entity.AnnouncementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<AnnouncementEntity,Long> {

    @Query("SELECT a FROM AnnouncementEntity a WHERE a.company = :companyId AND a.title LIKE %:title%")
    List<AnnouncementEntity> findByCompanyIdAndTitleContaining(@Param("companyId") Long companyId, @Param("title") String title);

    List<AnnouncementEntity> findByCompanyIdAndIsActiveAndValidUntilAfter(Long companyId, Boolean isActive, Instant currentDate);

    List<AnnouncementEntity> findByCompanyId(Long companyId);
}
