package management.sedef.subscriptionPlan.model.mapper;

import management.sedef.common.model.entity.mapper.BaseMapper;

import management.sedef.subscriptionPlan.model.SubscriptionPlan;
import management.sedef.subscriptionPlan.model.entity.SubscriptionPlanEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionPlanEntityToDomainMapper extends BaseMapper<SubscriptionPlanEntity, SubscriptionPlan> {

    static SubscriptionPlanEntityToDomainMapper initialize(){
        return Mappers.getMapper(SubscriptionPlanEntityToDomainMapper.class);
    }
}