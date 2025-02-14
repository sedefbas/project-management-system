package management.sedef.priority.service;

import management.sedef.priority.model.Priority;
import management.sedef.priority.model.request.PriorityRequest;

import java.util.List;

public interface PriorityService {
    List<Priority> findPrioritiesByCompanyId(Long companyId);
    void save(PriorityRequest request);
    void delete(Long priorityId);
    Priority findById(Long priorityId);
}
