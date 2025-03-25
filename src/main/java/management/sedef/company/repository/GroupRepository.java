package management.sedef.company.repository;

import management.sedef.company.model.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity,Long> {
    List<GroupEntity> findByCompany_Id(Long companyId);
    List<GroupEntity> findByProject_Id(Long projectId);
}
