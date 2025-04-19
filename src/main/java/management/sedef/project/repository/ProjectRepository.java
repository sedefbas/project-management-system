package management.sedef.project.repository;

import management.sedef.company.model.entity.GroupEntity;
import management.sedef.project.model.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<ProjectEntity,Long> {

    @Query("SELECT p FROM ProjectEntity p WHERE p.id = :id AND p.company.id = :companyId")
    Optional<ProjectEntity> findByIdAndCompanyId(@Param("id") Long id, @Param("companyId") Long companyId);

    @Query("SELECT p FROM ProjectEntity p WHERE p.company.id = :companyId")
    List<ProjectEntity> findAllByCompanyId(@Param("companyId") Long companyId);

    @Query("SELECT COUNT(p) FROM ProjectEntity p WHERE p.company.id = :companyId")
    int countProjectsByCompanyId(@Param("companyId") Long companyId);
}
