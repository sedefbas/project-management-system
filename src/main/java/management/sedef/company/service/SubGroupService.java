package management.sedef.company.service;

import management.sedef.company.model.SubGroup;
import management.sedef.company.model.request.SubGroupRequest;
import management.sedef.company.model.request.SubGroupUpdateRequest;

import java.util.List;

public interface SubGroupService {
    void create(SubGroupRequest request);
    void delete(Long id);
    void update(Long id, SubGroupUpdateRequest request);
    SubGroup findById(Long id);
    List<SubGroup> findByGroupId(Long groupId);
}
