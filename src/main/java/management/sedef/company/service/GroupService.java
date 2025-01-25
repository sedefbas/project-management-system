package management.sedef.company.service;

import management.sedef.company.model.Group;
import management.sedef.company.model.request.GroupRequest;
import management.sedef.company.model.request.GroupUpdateRequest;

import java.util.List;

public interface GroupService {

    void create(GroupRequest request);
    void delete(Long id);
    void update(Long id,GroupUpdateRequest request);
    List<Group> findByCompanyId(Long companyId);
    Group findById(Long id);
}
