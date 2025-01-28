package management.sedef.company.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.Group;
import management.sedef.company.model.mapper.groupmapper.GroupToExtendedGroupResponseMapper;
import management.sedef.company.model.mapper.groupmapper.GroupToGroupResponseMapper;
import management.sedef.company.model.request.GroupRequest;
import management.sedef.company.model.request.GroupUpdateRequest;
import management.sedef.company.model.response.ExtendedGroupResponse;
import management.sedef.company.model.response.GroupResponse;
import management.sedef.company.service.GroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupToGroupResponseMapper groupToGroupResponseMapper = GroupToGroupResponseMapper.initialize();


    @PostMapping
    @PreAuthorize("hasAnyAuthority('group:create')")
    public SuccessResponse<Void> create(@PathVariable Long companyId, @RequestBody GroupRequest groupRequest){
      groupService.create(companyId,groupRequest);
      return SuccessResponse.success();
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('group:detail')")
    public SuccessResponse<List<GroupResponse>> findByCompanyId(@PathVariable Long companyId) {
        List<Group> groups = groupService.findByCompanyId(companyId);
        System.out.println(groups);
        List<GroupResponse> groupResponses = groups.stream()
                .map(groupToGroupResponseMapper::map)
                .collect(Collectors.toList());

        return SuccessResponse.success(groupResponses);
    }

    @GetMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('group:detail')")
    public SuccessResponse<ExtendedGroupResponse> findById(@PathVariable Long groupId) {
        Group group = groupService.findById(groupId);
        ExtendedGroupResponse response = GroupToExtendedGroupResponseMapper.INSTANCE.toExtendedGroupResponse(group);
        return SuccessResponse.success(response);
    }

    @DeleteMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('group:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long groupId) {
        groupService.delete(groupId);
        return SuccessResponse.success(); // Başarılı dönüş
    }

    @PutMapping("/{groupId}")
    @PreAuthorize("hasAnyAuthority('group:update')")
    public SuccessResponse<Void> update(@PathVariable Long groupId,@Valid @RequestBody GroupUpdateRequest groupUpdateRequest) {
        groupService.update(groupId, groupUpdateRequest);
        return SuccessResponse.success();
    }


}
