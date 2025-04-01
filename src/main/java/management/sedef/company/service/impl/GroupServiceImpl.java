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
import management.sedef.project.model.Project;
import management.sedef.project.port.projectPort.ProjectReadAdapter;
import management.sedef.project.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository repository;
    private final CompanyServiceImpl companyService;
    private final ProjectReadAdapter readAdapter;
    private final GroupToEntityMapper groupToEntityMapper = GroupToEntityMapper.initialize();
    private final GroupEntityToDomainMapper groupEntityToDomainMapper ;

    @Override
    public void create(Long companyId,Long projectId, GroupRequest request) {
        Company company = companyService.findCompanyById(companyId);
        Project project = readAdapter.findByIdAndCompanyId(projectId,companyId);
        Group group = Group.builder()
                .name(request.getName())
                .color(request.getColor())
                .company(company)
                .project(project)
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
    public List<Group> findByProjectId(Long projectId) {
        List<GroupEntity> groupEntities = repository.findByProject_Id(projectId);

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
