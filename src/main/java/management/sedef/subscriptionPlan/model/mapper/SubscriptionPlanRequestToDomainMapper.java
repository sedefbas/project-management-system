package management.sedef.subscription.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.request.SubscriptionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionRequestToSubscriptionMapper extends BaseMapper<SubscriptionRequest, Subscription> {

    static SubscriptionRequestToSubscriptionMapper initialize(){
        return Mappers.getMapper(SubscriptionRequestToSubscriptionMapper.class);
    }
}
