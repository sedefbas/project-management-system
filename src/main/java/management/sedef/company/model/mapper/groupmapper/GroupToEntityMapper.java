package management.sedef.company.model.mapper.groupmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Group;
import management.sedef.company.model.entity.GroupEntity;
import management.sedef.company.model.mapper.companyusermapper.CompanyUserToEntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupToEntityMapper extends BaseMapper<Group, GroupEntity> {
    static GroupToEntityMapper initialize(){
        return Mappers.getMapper(GroupToEntityMapper.class);
    }

}
