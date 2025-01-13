package management.sedef.company.model.mapper.addressmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Address;
import management.sedef.company.model.request.AddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressRequestToDomainMapper extends BaseMapper<AddressRequest, Address> {
    static AddressRequestToDomainMapper initialize(){
        return Mappers.getMapper(AddressRequestToDomainMapper.class);
    }
}
