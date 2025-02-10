package management.sedef.priority.port.priority;


import management.sedef.priority.model.Priority;
import java.util.List;


public interface PriorityReadPort {
    List<Priority> findPrioritiesByCompanyId(Long companyId);
    Priority findById(Long priorityId);
}