package management.sedef.subscription.port.adapter;

import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.entity.SubscriptionEntity;
import management.sedef.subscription.model.mapper.SubscriptionEntityToSubsriptionMapper;
import management.sedef.subscription.model.mapper.SubscriptionToSubcriptionEntityMapper;
import management.sedef.subscription.port.SubscriptionDeletePort;
import management.sedef.subscription.port.SubscriptionReadPort;
import management.sedef.subscription.port.SubscriptionSavePort;
import management.sedef.subscription.repository.SubscriptionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubscriptionAdapter implements SubscriptionSavePort, SubscriptionReadPort, SubscriptionDeletePort {

    private final SubscriptionEntityToSubsriptionMapper subscriptionEntityToSubsriptionMapper = SubscriptionEntityToSubsriptionMapper.initialize();
    private  final SubscriptionToSubcriptionEntityMapper subscriptionToSubcriptionEntityMapper = SubscriptionToSubcriptionEntityMapper.initialize();
    private final SubscriptionRepository subscriptionRepository;

    @Override
    public Subscription save(Subscription subscription) {
        final SubscriptionEntity subscriptionEntity = subscriptionToSubcriptionEntityMapper.map(subscription);
        final  SubscriptionEntity savedSubscriptionEntity = subscriptionRepository.save(subscriptionEntity);
        return subscriptionEntityToSubsriptionMapper.map(savedSubscriptionEntity);
    }

    @Override
    public List<Subscription> findAll() {
        return subscriptionRepository.findAll().stream().map(subscriptionEntityToSubsriptionMapper::map).toList();
    }

    @Override
    public Optional<Subscription> findById(Long id) {
        return subscriptionRepository.findById(id)
                .map(subscriptionEntityToSubsriptionMapper::map);
    }

    @Override
    public void delete(final Subscription subscription) {
        final SubscriptionEntity subscriptionEntity = subscriptionToSubcriptionEntityMapper.map(subscription);
        subscriptionRepository.delete(subscriptionEntity);
    }
}
