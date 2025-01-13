package management.sedef.subscriptionPlan.service.ımpl;


import lombok.RequiredArgsConstructor;
import management.sedef.subscriptionPlan.exception.SubscriptionNotFoundException;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;
import management.sedef.subscriptionPlan.model.mapper.SubscriptionPlanRequestToDomainMapper;
import management.sedef.subscriptionPlan.model.request.SubscriptionPlanRequest;
import management.sedef.subscriptionPlan.port.SubscriptionPlanDeletePort;
import management.sedef.subscriptionPlan.port.SubscriptionPlanReadPort;
import management.sedef.subscriptionPlan.port.SubscriptionPlanSavePort;
import management.sedef.subscriptionPlan.repository.SubscriptionPlanRepository;
import management.sedef.subscriptionPlan.service.SubscriptionPlanService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanSavePort subscriptionPlanSavePort;
    private final SubscriptionPlanReadPort subscriptionPlanReadPort;
    private final SubscriptionPlanDeletePort subscriptionPlanDeletePort;
    private final SubscriptionPlanRequestToDomainMapper subscriptionPlanRequestToDomainMapper = SubscriptionPlanRequestToDomainMapper.initialize();
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlan findById(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanReadPort.findById(id)
                .orElseThrow(()-> new SubscriptionNotFoundException(id));
        return subscriptionPlan;
    }

    @Override
    public List<SubscriptionPlan> findAll() {
        return subscriptionPlanReadPort.findAll();
    }

    @Override
    public void create(SubscriptionPlanRequest request) {
         final SubscriptionPlan subscriptionPlan = subscriptionPlanRequestToDomainMapper.map(request);
         subscriptionPlanSavePort.save(subscriptionPlan);
    }

    @Override
    public void delete(Long id) {
        SubscriptionPlan subscriptionPlan = subscriptionPlanReadPort.findById(id)
                .orElseThrow(()-> new SubscriptionNotFoundException(id));

        subscriptionPlanDeletePort.delete(subscriptionPlan);
    }

    @Override
    public void update(Long id, SubscriptionPlanRequest subscriptionRequest) {
       final SubscriptionPlan subscriptionPlan = subscriptionPlanReadPort.findById(id)
                 .orElseThrow(()-> new SubscriptionNotFoundException(id));

       subscriptionPlan.setStatus(subscriptionRequest.getStatus());
       subscriptionPlan.setDescription(subscriptionRequest.getDescription());
       subscriptionPlan.setMaxProjects(subscriptionRequest.getMaxProjects());
       subscriptionPlan.setMaxTasks(subscriptionRequest.getMaxTasks());
       subscriptionPlan.setFeatures(subscriptionRequest.getFeatures());
       subscriptionPlan.setPrice(subscriptionRequest.getPrice());
       subscriptionPlan.setMaxTasks(subscriptionRequest.getMaxUsers());
       subscriptionPlanSavePort.save(subscriptionPlan);
    }

    public SubscriptionPlan findByStatus(SubscriptionPlanStatus status) {
        return subscriptionPlanReadPort.findByStatus(status)
                .orElseThrow(() -> new IllegalArgumentException("Subscription plan not found for status: " + status));
    }



}
