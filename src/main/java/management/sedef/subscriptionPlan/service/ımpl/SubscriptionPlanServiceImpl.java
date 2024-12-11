package management.sedef.subscription.service.ımpl;

import lombok.RequiredArgsConstructor;
import management.sedef.subscription.exception.SubscriptionNotFoundException;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.enums.SubscriptionPlan;
import management.sedef.subscription.model.mapper.SubscriptionRequestToSubscriptionMapper;
import management.sedef.subscription.model.request.SubscriptionRequest;
import management.sedef.subscription.port.SubscriptionDeletePort;
import management.sedef.subscription.port.SubscriptionReadPort;
import management.sedef.subscription.port.SubscriptionSavePort;
import management.sedef.subscription.service.SubscriptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionSavePort subscriptionSavePort;
    private final SubscriptionReadPort subscriptionReadPort;
    private final SubscriptionDeletePort subscriptionDeletePort;
    private final SubscriptionRequestToSubscriptionMapper subscriptionRequestToSubscriptionMapper = SubscriptionRequestToSubscriptionMapper.initialize();

    @Override
    public Subscription findById(Long id) {
        Subscription subscription = subscriptionReadPort.findById(id)
                .orElseThrow(()-> new SubscriptionNotFoundException(id));
        return subscription;
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionReadPort.findAll();
    }

    @Override
    public void create(SubscriptionRequest request) {
         final Subscription subscription = subscriptionRequestToSubscriptionMapper.map(request);
         subscriptionSavePort.save(subscription);
    }

    @Override
    public void delete(Long id) {
        Subscription subscription = subscriptionReadPort.findById(id)
                .orElseThrow(()-> new SubscriptionNotFoundException(id));

        subscriptionDeletePort.delete(subscription);
    }

    @Override
    public void update(Long id, SubscriptionRequest subscriptionRequest) {
       final  Subscription subscription = subscriptionReadPort.findById(id)
                 .orElseThrow(()-> new SubscriptionNotFoundException(id));

       subscription.setSubscriptionPlan(SubscriptionPlan.valueOf(subscriptionRequest.getSubscriptionPlan()));
       subscription.setDescription(subscriptionRequest.getDescription());
       subscription.setMaxProjects(subscriptionRequest.getMaxProjects());
       subscription.setMaxTasks(subscriptionRequest.getMaxTasks());
       subscription.setFeatures(subscriptionRequest.getFeatures());
       subscription.setPrice(subscriptionRequest.getPrice());
       subscription.setMaxTasks(subscriptionRequest.getMaxUsers());
       subscriptionSavePort.save(subscription);
    }


}
