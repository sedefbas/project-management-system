package management.sedef.company.controller;

import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.company.model.SubGroup;
import management.sedef.company.model.mapper.subgroupmapper.SubGroupToResponseMapper;
import management.sedef.company.model.request.SubGroupRequest;
import management.sedef.company.model.request.SubGroupUpdateRequest;
import management.sedef.company.model.response.SubGroupResponse;
import management.sedef.company.service.SubGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/v1/company-sub-group")
@RequiredArgsConstructor
public class SubGroupController {
    private final SubGroupService service;
    private final SubGroupToResponseMapper subGroupToResponseMapper = SubGroupToResponseMapper.initialize();

    @PostMapping
    public SuccessResponse<Void> create(@RequestBody SubGroupRequest request){
        service.create(request);
        return SuccessResponse.success();
    }

    @GetMapping("/{groupId}")
    public SuccessResponse<List<SubGroupResponse>> findByGroupId(@PathVariable Long groupId) {
        List<SubGroup> subGroups = service.findByGroupId(groupId);

        List<SubGroupResponse> subGroupResponses = subGroups.stream()
                .map(subGroupToResponseMapper::map)
                .collect(Collectors.toList());

        return SuccessResponse.success(subGroupResponses);
    }

    @DeleteMapping("/{id}")
    public SuccessResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return SuccessResponse.success();
    }

    @PutMapping("/{id}")
    public SuccessResponse<Void> update(@PathVariable Long id, @RequestBody SubGroupUpdateRequest request) {
        service.update(id, request);
        return SuccessResponse.success();
    }

    @GetMapping("/subgroup/{id}")
    public SuccessResponse<SubGroupResponse> findById(@PathVariable Long id) {
        SubGroup subGroup = service.findById(id);
        SubGroupResponse subGroupResponse = subGroupToResponseMapper.map(subGroup);
        return SuccessResponse.success(subGroupResponse);
    }


}
