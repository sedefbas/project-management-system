package management.sedef.priority.port.adapter;


import lombok.RequiredArgsConstructor;
import management.sedef.priority.exception.CompanyPriorityNotFoundException;
import management.sedef.priority.model.CompanyPriority;
import management.sedef.priority.model.entity.CompanyPriorityEntity;
import management.sedef.priority.model.mapper.companyPriority.CompanyPriorityEntityToDomainMapper;
import management.sedef.priority.model.mapper.companyPriority.CompanyPriorityToEntityMapper;
import management.sedef.priority.port.companypriority.CompanyPriorityDeletePort;
import management.sedef.priority.port.companypriority.CompanyPriorityReadPort;
import management.sedef.priority.port.companypriority.CompanyPrioritySavePort;
import management.sedef.priority.repository.CompanyPriorityRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CompanyPriorityAdapter implements CompanyPrioritySavePort, CompanyPriorityDeletePort, CompanyPriorityReadPort {

    private final CompanyPriorityRepository repository;
    private final CompanyPriorityToEntityMapper companyPriorityToEntityMapper = CompanyPriorityToEntityMapper.initialize();
    private final CompanyPriorityEntityToDomainMapper companyPriorityEntityToDomainMapper = CompanyPriorityEntityToDomainMapper.initialize();

    @Override
    public CompanyPriority delete(CompanyPriority companyPriority) {
        CompanyPriorityEntity companyPriorityEntity = companyPriorityToEntityMapper.map(companyPriority);
        repository.delete(companyPriorityEntity);
        return companyPriority;
    }

    @Override
    public CompanyPriority findByCompanyIdAndPriorityId(Long companyId, Long priorityId) {
        CompanyPriorityEntity companyPriorityEntity = repository.findByCompanyIdAndPriorityId(companyId,priorityId)
                .orElseThrow(() -> new CompanyPriorityNotFoundException("Company label not found for companyId: " + companyId + " and labelId: " + priorityId));
        return companyPriorityEntityToDomainMapper.map(companyPriorityEntity);
    }

    @Override
    public CompanyPriority save(CompanyPriority companyPriority) {
        CompanyPriorityEntity companyPriorityEntity = companyPriorityToEntityMapper.map(companyPriority);
        repository.save(companyPriorityEntity);
        return companyPriority;
    }
}
