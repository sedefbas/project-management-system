package management.sedef.subscription.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import management.sedef.common.model.entity.response.SuccessResponse;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.mapper.SubscriptionToResponseMapper;
import management.sedef.subscription.model.request.SubscriptionRequest;
import management.sedef.subscription.model.response.SubscriptionResponse;
import management.sedef.subscription.service.SubscriptionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(name = "/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionToResponseMapper subscriptionToResponseMapper = SubscriptionToResponseMapper.initialize();


    @GetMapping("/subscription/{id}")
   // @PreAuthorize("hasAuthority('subscription:detail')")
    SuccessResponse<SubscriptionResponse> findById(@PathVariable @Positive Long id) {
        Subscription subscription = subscriptionService.findById(id);
        SubscriptionResponse subscriptionResponse = subscriptionToResponseMapper.map(subscription);
        return SuccessResponse.success(subscriptionResponse);
    }

    @GetMapping("/subscriptions")
// @PreAuthorize("hasAuthority('subscription:detail')")
    public SuccessResponse<List<SubscriptionResponse>> findAll() {

        List<Subscription> subscriptionList = subscriptionService.findAll();
        List<SubscriptionResponse> subscriptionResponses = subscriptionList.stream()
                .map(subscription -> subscriptionToResponseMapper.map(subscription))
                .toList();
        return SuccessResponse.success(subscriptionResponses);
    }


    @PostMapping("/subscription")
    //@PreAuthorize("hasAuthority('category:create')")
    SuccessResponse<Void> create(@RequestBody @Valid SubscriptionRequest request) {
        subscriptionService.create(request);
        return SuccessResponse.success();
    }

    @PutMapping("/subscription/{id}")
    //@PreAuthorize("hasAuthority('subscription:update')")
    SuccessResponse<Void> update(@PathVariable @Positive Long id, @RequestBody @Valid SubscriptionRequest request) {
        subscriptionService.update(id, request);
        return SuccessResponse.success();
    }

    @DeleteMapping("/subscription/{id}")
   // @PreAuthorize("hasAuthority('subscription:delete')")
    SuccessResponse<Void> delete(@PathVariable @Positive Long id) {
        subscriptionService.delete(id);
        return SuccessResponse.success();
    }


}
