package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.company.model.Group;
import management.sedef.company.model.SubGroup;
import management.sedef.company.model.entity.SubGroupEntity;
import management.sedef.company.model.mapper.subgroupmapper.SubGroupEntityToDomainMapper;
import management.sedef.company.model.mapper.subgroupmapper.SubGroupToEntityMapper;
import management.sedef.company.model.request.SubGroupRequest;
import management.sedef.company.model.request.SubGroupUpdateRequest;
import management.sedef.company.repository.SubGroupRepository;
import management.sedef.company.service.GroupService;
import management.sedef.company.service.SubGroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubGroupServiceImpl implements SubGroupService {

    private final SubGroupRepository repository;
    private final SubGroupToEntityMapper subGroupToEntityMapper = SubGroupToEntityMapper.initialize();
    private final SubGroupEntityToDomainMapper subGroupEntityToDomainMapper = SubGroupEntityToDomainMapper.initialize();
    private final GroupService groupService;


    @Override
    public void create(SubGroupRequest request) {
        Group group = groupService.findById(request.getGroupId());

        SubGroup subGroup = SubGroup.builder()
                .name(request.getName())
                .group(group)
                .build();

        SubGroupEntity subGroupEntity = subGroupToEntityMapper.map(subGroup);
        repository.save(subGroupEntity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, SubGroupUpdateRequest request) {
        SubGroupEntity subGroupEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubGroup not found"));
        subGroupEntity.setName(request.getName());
        repository.save(subGroupEntity);
    }

    @Override
    public SubGroup findById(Long id) {
        SubGroupEntity subGroupEntity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("SubGroup not found"));
        return subGroupEntityToDomainMapper.map(subGroupEntity);
    }

    @Override
    public List<SubGroup> findByGroupId(Long groupId) {
        List<SubGroupEntity> subGroupEntities = repository.findByGroup_Id(groupId);

        return subGroupEntities.stream()
                .map(subGroupEntityToDomainMapper::map)
                .collect(Collectors.toList());
    }
}
