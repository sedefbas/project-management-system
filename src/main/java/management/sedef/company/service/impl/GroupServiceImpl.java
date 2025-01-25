package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Company;
import management.sedef.company.model.Group;
import management.sedef.company.model.entity.CompanyEntity;
import management.sedef.company.model.entity.GroupEntity;
import management.sedef.company.model.mapper.groupmapper.GroupEntityToDomainMapper;
import management.sedef.company.model.mapper.groupmapper.GroupToEntityMapper;
import management.sedef.company.model.request.GroupRequest;
import management.sedef.company.model.request.GroupUpdateRequest;
import management.sedef.company.repository.GroupRepository;
import management.sedef.company.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;
    private final CompanyServiceImpl companyService;
    private final GroupToEntityMapper groupToEntityMapper = GroupToEntityMapper.initialize();
    private final GroupEntityToDomainMapper groupEntityToDomainMapper = GroupEntityToDomainMapper.initialize();


    @Override
    public void create(GroupRequest request) {
        Company company = companyService.findCompanyById(request.getCompanyId());
        Group group = Group.builder()
                .name(request.getName())
                .company(company)
                .build();
        GroupEntity groupEntity = groupToEntityMapper.map(group);
        repository.save(groupEntity);
    }

    @Override
    public void delete(Long id) {
        GroupEntity groupEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        repository.delete(groupEntity);
    }

    @Override
    public void update(Long id,GroupUpdateRequest request) {
        GroupEntity groupEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        groupEntity.setName(request.getName());
        repository.save(groupEntity);
    }

    @Override
    public List<Group> findByCompanyId(Long companyId) {
        List<GroupEntity> groupEntities = repository.findByCompany_Id(companyId);

        return groupEntities.stream()
                .map(groupEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public Group findById(Long id) {
        GroupEntity groupEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));
        return groupEntityToDomainMapper.map(groupEntity);
    }
}
