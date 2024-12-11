package management.sedef.subscription.controller;

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
@RequestMapping(path = "/api/v1/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final SubscriptionToResponseMapper subscriptionToResponseMapper = SubscriptionToResponseMapper.initialize();


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBSCRIPTION_FIND_BY_ID')")
    SuccessResponse<SubscriptionResponse> findById(@PathVariable @Positive Long id) {
        Subscription subscription = subscriptionService.findById(id);
        SubscriptionResponse subscriptionResponse = subscriptionToResponseMapper.map(subscription);
        return SuccessResponse.success(subscriptionResponse);
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('SUBSCRIPTION_FIND_ALL')")
    public SuccessResponse<List<SubscriptionResponse>> findAll() {

        List<Subscription> subscriptionList = subscriptionService.findAll();
        List<SubscriptionResponse> subscriptionResponses = subscriptionList.stream()
                .map(subscription -> subscriptionToResponseMapper.map(subscription))
                .toList();
        return SuccessResponse.success(subscriptionResponses);
    }


    @PostMapping()
    @PreAuthorize("hasAuthority('SUBSCRIPTION_CREATE')")
    SuccessResponse<Void> create(@RequestBody  SubscriptionRequest request) {
        subscriptionService.create(request);
        return SuccessResponse.success();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBSCRIPTION_UPDATE')")
    SuccessResponse<Void> update(@PathVariable @Positive Long id, @RequestBody SubscriptionRequest request) {
        subscriptionService.update(id, request);
        return SuccessResponse.success();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SUBSCRIPTION_DELETE')")
    SuccessResponse<Void> delete(@PathVariable @Positive Long id) {
        subscriptionService.delete(id);
        return SuccessResponse.success();
    }


}
