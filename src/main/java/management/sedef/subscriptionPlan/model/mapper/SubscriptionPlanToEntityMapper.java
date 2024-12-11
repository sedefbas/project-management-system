package management.sedef.subscription.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.entity.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionToSubcriptionEntityMapper  extends BaseMapper<Subscription, SubscriptionEntity> {

    static SubscriptionToSubcriptionEntityMapper initialize(){
        return Mappers.getMapper(SubscriptionToSubcriptionEntityMapper.class);
    }

}
