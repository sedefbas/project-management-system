package management.sedef.subscriptionPlan.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.subscriptionPlan.model.SubscriptionPlan;

import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;
import management.sedef.subscriptionPlan.model.enums.SubscriptionPlanStatus;
import management.sedef.subscriptionPlan.model.mapper.SubscriptionPlanEntityToDomainMapper;
import management.sedef.subscriptionPlan.model.mapper.SubscriptionPlanToEntityMapper;
import management.sedef.subscriptionPlan.port.SubscriptionPlanDeletePort;
import management.sedef.subscriptionPlan.port.SubscriptionPlanReadPort;
import management.sedef.subscriptionPlan.port.SubscriptionPlanSavePort;
import management.sedef.subscriptionPlan.repository.SubscriptionPlanRepository;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubscriptionPlanAdapter implements SubscriptionPlanSavePort, SubscriptionPlanReadPort, SubscriptionPlanDeletePort {

    private final SubscriptionPlanEntityToDomainMapper subscriptionEntityToSubsriptionMapper = SubscriptionPlanEntityToDomainMapper.initialize();
    private  final SubscriptionPlanToEntityMapper subscriptionPlanToSubcriptionEntityMapper = SubscriptionPlanToEntityMapper.initialize();
    private final SubscriptionPlanRepository subscriptionPlanRepository;

    @Override
    public SubscriptionPlan save(SubscriptionPlan subscriptionPlan) {
        final SubscriptionPlanEntity subscriptionPlanEntity = subscriptionPlanToSubcriptionEntityMapper.map(subscriptionPlan);
        final  SubscriptionPlanEntity savedSubscriptionEntity = subscriptionPlanRepository.save(subscriptionPlanEntity);
        return subscriptionEntityToSubsriptionMapper.map(savedSubscriptionEntity);
    }

    @Override
    public List<SubscriptionPlan> findAll() {
        return subscriptionPlanRepository.findAll().stream().map(subscriptionEntityToSubsriptionMapper::map).toList();
    }

    @Override
    public Optional<SubscriptionPlan> findById(Long id) {
        return subscriptionPlanRepository.findById(id)
                .map(subscriptionEntityToSubsriptionMapper::map);
    }

    @Override
    public Optional<SubscriptionPlan> findByStatus(SubscriptionPlanStatus status) {
        Optional<SubscriptionPlanEntity> subscriptionPlanEntityOptional = subscriptionPlanRepository.findByStatus(status);
        return subscriptionPlanEntityOptional.map(subscriptionEntityToSubsriptionMapper::map);
    }


    @Override
    public void delete(final SubscriptionPlan subscriptionPlan) {
        final SubscriptionPlanEntity subscriptionEntity = subscriptionPlanToSubcriptionEntityMapper.map(subscriptionPlan);
        subscriptionPlanRepository.delete(subscriptionEntity);
    }
}
