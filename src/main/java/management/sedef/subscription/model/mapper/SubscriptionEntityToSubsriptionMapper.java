package management.sedef.subscription.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.entity.SubscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionEntityToSubsriptionMapper extends BaseMapper<SubscriptionEntity, Subscription> {

    static SubscriptionEntityToSubsriptionMapper initialize(){
        return Mappers.getMapper(SubscriptionEntityToSubsriptionMapper.class);
    }
}
