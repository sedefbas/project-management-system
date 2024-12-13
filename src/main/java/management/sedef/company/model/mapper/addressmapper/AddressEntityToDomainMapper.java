package management.sedef.company.model.mapper.addressmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Address;
import management.sedef.company.model.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressEntityToDomainMapper extends BaseMapper<AddressEntity, Address> {
    static AddressEntityToDomainMapper initialize(){
        return Mappers.getMapper(AddressEntityToDomainMapper.class);
    }
}
