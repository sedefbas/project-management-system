package management.sedef.subscriptionPlan.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;

import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.request.SubscriptionPlanRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionPlanRequestToDomainMapper extends BaseMapper<SubscriptionPlanRequest,SubscriptionPlan> {

    static SubscriptionPlanRequestToDomainMapper initialize(){
        return Mappers.getMapper(SubscriptionPlanRequestToDomainMapper.class);
    }
}
