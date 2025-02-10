package management.sedef.priority.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.mapper.priority.PriorityRequestToDomainMapper;
import management.sedef.priority.model.request.PriorityRequest;
import management.sedef.priority.port.priority.PriorityDeletePort;
import management.sedef.priority.port.priority.PriorityReadPort;
import management.sedef.priority.port.priority.PrioritySavePort;
import management.sedef.priority.service.PriorityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriorityServiceImpl implements PriorityService {

    private final PriorityReadPort priorityReadPort;
    private final PriorityDeletePort priorityDeletePort;
    private final PrioritySavePort prioritySavePort;
    private final PriorityRequestToDomainMapper priorityRequestToDomainMapper = PriorityRequestToDomainMapper.initialize();


    @Override
    public List<Priority> findPrioritiesByCompanyId(Long companyId) {
        return priorityReadPort.findPrioritiesByCompanyId(companyId);
    }

    @Override
    public void save(PriorityRequest request) {
        Priority priority = priorityRequestToDomainMapper.map(request);
        priority.setIsDefault(true);
        prioritySavePort.save(priority);
    }

    @Override
    public void delete(Long priorityId) {
        Priority priority =  priorityReadPort.findById(priorityId);
        priorityDeletePort.delete(priority);
    }

}
