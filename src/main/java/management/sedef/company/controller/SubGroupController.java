package management.sedef.company.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.SubGroup;
import management.sedef.company.model.mapper.subgroupmapper.SubGroupToResponseMapper;
import management.sedef.company.model.request.SubGroupRequest;
import management.sedef.company.model.request.SubGroupUpdateRequest;
import management.sedef.company.model.response.SubGroupResponse;
import management.sedef.company.service.SubGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/company/{companyId}/group/{groupId}/subgroup")
@RequiredArgsConstructor
public class SubGroupController {

    private final SubGroupService service;
    private final SubGroupToResponseMapper subGroupToResponseMapper = SubGroupToResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('subgroup:create')")
    public SuccessResponse<Void> create(@RequestBody SubGroupRequest request, @PathVariable Long groupId){
        service.create(request,groupId);
        return SuccessResponse.success();
    }

    @GetMapping()
    @PreAuthorize("hasAnyAuthority('subgroup:detail')")
    public SuccessResponse<List<SubGroupResponse>> findByGroupId(@PathVariable Long groupId) {
        List<SubGroup> subGroups = service.findByGroupId(groupId);

        List<SubGroupResponse> subGroupResponses = subGroups.stream()
                .map(subGroupToResponseMapper::map)
                .collect(Collectors.toList());
        return SuccessResponse.success(subGroupResponses);
    }


    @DeleteMapping("/{subgroupId}")
    @PreAuthorize("hasAnyAuthority('subgroup:delete')")
    public SuccessResponse<Void> delete(@PathVariable Long subgroupId) {
        service.delete(subgroupId);
        return SuccessResponse.success();
    }

    @PutMapping("/{subgroupId}")
    @PreAuthorize("hasAnyAuthority('subgroup:update')")
    public SuccessResponse<Void> update(@PathVariable Long subgroupId, @RequestBody SubGroupUpdateRequest request) {
        service.update(subgroupId, request);
        return SuccessResponse.success();
    }

    @GetMapping("{subgroupId}")
    @PreAuthorize("hasAnyAuthority('subgroup:detail')")
    public SuccessResponse<SubGroupResponse> findById(@PathVariable Long subgroupId) {
        SubGroup subGroup = service.findById(subgroupId);
        SubGroupResponse subGroupResponse = subGroupToResponseMapper.map(subGroup);
        return SuccessResponse.success(subGroupResponse);
    }


}
