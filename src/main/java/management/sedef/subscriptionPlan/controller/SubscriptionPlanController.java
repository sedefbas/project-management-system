package management.sedef.subscriptionPlan.controller;

import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;
import management.sedef.subscriptionPlan.model.mapper.SubscriptionPlanToResponseMapper;
import management.sedef.subscriptionPlan.model.request.SubscriptionPlanRequest;
import management.sedef.subscriptionPlan.model.response.SubscriptionPlanResponse;

import management.sedef.subscriptionPlan.service.SubscriptionPlanService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/subscriptionPlan")
@RequiredArgsConstructor
public class SubscriptionPlanController {

    private final SubscriptionPlanService subscriptionService;
    private final SubscriptionPlanToResponseMapper subscriptionToResponseMapper = SubscriptionPlanToResponseMapper.initialize();

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('subscription:detail')")
    SuccessResponse<SubscriptionPlanResponse> findById(@PathVariable @Positive Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionService.findById(id);
        SubscriptionPlanResponse subscriptionPlanResponse = subscriptionToResponseMapper.map(subscriptionPlan);
        return SuccessResponse.success(subscriptionPlanResponse);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('subscription:list')")
    public SuccessResponse<List<SubscriptionPlanResponse>> findAll() {
        List<SubscriptionPlan> subscriptionPlanList = subscriptionService.findAll();
        List<SubscriptionPlanResponse> subscriptionPlanRespons = subscriptionPlanList.stream()
                .map(subscription -> subscriptionToResponseMapper.map(subscription))
                .toList();
        return SuccessResponse.success(subscriptionPlanRespons);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('subscription:create')")
    SuccessResponse<Void> create(@RequestBody SubscriptionPlanRequest request) {
        subscriptionService.create(request);
        return SuccessResponse.success();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('subscription:update')")
    SuccessResponse<Void> update(@PathVariable @Positive Long id, @RequestBody SubscriptionPlanRequest request) {
        subscriptionService.update(id, request);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('subscription:delete')")
    SuccessResponse<Void> delete(@PathVariable @Positive Long id) {
        subscriptionService.delete(id);
        return SuccessResponse.success();
    }

    @GetMapping("/status/id")
    @PreAuthorize("hasAuthority('subscription:detail')")
    SuccessResponse<SubscriptionPlanResponse>  findByStatus(SubscriptionPlanStatus status) {
        SubscriptionPlan subscriptionPlan = subscriptionService.findByStatus(status);
        SubscriptionPlanResponse subscriptionPlanRespons = subscriptionToResponseMapper.map(subscriptionPlan);
        return SuccessResponse.success(subscriptionPlanRespons);
    }
}
