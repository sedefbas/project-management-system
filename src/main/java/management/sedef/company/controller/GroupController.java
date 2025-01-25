package management.sedef.company.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.Group;
import management.sedef.company.model.mapper.groupmapper.GroupToGroupResponseMapper;
import management.sedef.company.model.request.GroupRequest;
import management.sedef.company.model.request.GroupUpdateRequest;
import management.sedef.company.model.response.GroupResponse;
import management.sedef.company.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/company-group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupToGroupResponseMapper groupToGroupResponseMapper = GroupToGroupResponseMapper.initialize();


    @PostMapping
    public SuccessResponse<Void> create(@RequestBody  GroupRequest groupRequest){
      groupService.create(groupRequest);
      return SuccessResponse.success();
    }

    @GetMapping("/{companyId}")
    public SuccessResponse<List<GroupResponse>> findByCompanyId(@PathVariable Long companyId) {
        List<Group> groups = groupService.findByCompanyId(companyId);

        List<GroupResponse> groupResponses = groups.stream()
                .map(groupToGroupResponseMapper::map)
                .collect(Collectors.toList());

        return SuccessResponse.success(groupResponses);
    }

    @GetMapping("/{id}")
    public SuccessResponse<Group> findById(@PathVariable Long id) {
        Group group = groupService.findById(id);
        return SuccessResponse.success(group);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<Void> delete(@PathVariable Long id) {
        groupService.delete(id);
        return SuccessResponse.success(); // Başarılı dönüş
    }

    @PutMapping("/{id}")
    public SuccessResponse<Void> update(@PathVariable Long id,@Valid @RequestBody GroupUpdateRequest groupUpdateRequest) {
        groupService.update(id, groupUpdateRequest);
        return SuccessResponse.success();
    }


}
