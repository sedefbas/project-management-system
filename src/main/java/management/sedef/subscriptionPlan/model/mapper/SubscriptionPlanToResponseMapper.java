package management.sedef.subscriptionPlan.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;

import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.response.SubscriptionPlanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionPlanToResponseMapper extends BaseMapper<SubscriptionPlan, SubscriptionPlanResponse> {

    static SubscriptionPlanToResponseMapper initialize(){
        return Mappers.getMapper(SubscriptionPlanToResponseMapper.class);
    }

}
