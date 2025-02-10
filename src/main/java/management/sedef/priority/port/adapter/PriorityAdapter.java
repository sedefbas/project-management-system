package management.sedef.priority.port.adapter;


import lombok.RequiredArgsConstructor;
import management.sedef.priority.exception.PriorityNotFoundException;
import management.sedef.priority.model.Priority;
import management.sedef.priority.model.entity.PriorityEntity;
import management.sedef.priority.model.mapper.priority.PriorityEntityToDomainMapper;
import management.sedef.priority.model.mapper.priority.PriorityToEntityMapper;
import management.sedef.priority.port.priority.PriorityDeletePort;
import management.sedef.priority.port.priority.PriorityReadPort;
import management.sedef.priority.port.priority.PrioritySavePort;
import management.sedef.priority.repository.PriorityRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PriorityAdapter implements PriorityDeletePort, PrioritySavePort, PriorityReadPort {

    private final PriorityRepository repository;
    private final PriorityEntityToDomainMapper priorityEntityToDomainMapper = PriorityEntityToDomainMapper.initialize();
    private final PriorityToEntityMapper priorityToEntityMapper = PriorityToEntityMapper.initialize();

    @Override
    public void delete(Priority priority) {
        final PriorityEntity priorityEntity = priorityToEntityMapper.map(priority);
        repository.delete(priorityEntity);
    }

    @Override
    public List<Priority> findPrioritiesByCompanyId(Long companyId) {
        List<PriorityEntity> priorityEntities = repository.findPrioritiesByCompanyId(companyId)
                .orElseThrow(() -> new PriorityNotFoundException("label not found"));

        List<Priority> priorities = priorityEntities.stream().map(labelEntity -> {
            Priority priority = priorityEntityToDomainMapper.map(labelEntity);

            priority.setIsDefault(labelEntity.getIsDefault());
            return priority;
        }).collect(Collectors.toList());
        return priorities;
    }

    @Override
    public Priority findById(Long priorityId) {
        return repository.findById(priorityId)
                .map(priorityEntityToDomainMapper::map)
                .orElseThrow(() -> new PriorityNotFoundException("Priority not found for ID: " + priorityId));
    }

    @Override
    public Priority save(Priority priority) {
        PriorityEntity priorityEntity = priorityToEntityMapper.map(priority);
        priorityEntity.setIsDefault(priority.getIsDefault());
        repository.save(priorityEntity);
        return priorityEntityToDomainMapper.map(priorityEntity);
    }
}
