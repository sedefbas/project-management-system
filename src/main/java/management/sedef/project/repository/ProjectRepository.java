package management.sedef.project.repository;

import management.sedef.project.model.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {
    @Query("SELECT COUNT(p) FROM ProjectEntity p WHERE p.company.id = :companyId")
    int countProjectsByCompanyId(@Param("companyId") Long companyId);
}
