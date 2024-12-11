package management.sedef.subscription.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.subscription.model.Subscription;
import management.sedef.subscription.model.response.SubscriptionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionToResponseMapper extends BaseMapper<Subscription, SubscriptionResponse> {

    static SubscriptionToResponseMapper initialize(){
        return Mappers.getMapper(SubscriptionToResponseMapper.class);
    }

}
