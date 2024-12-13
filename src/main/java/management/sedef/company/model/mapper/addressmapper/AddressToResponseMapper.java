package management.sedef.company.model.mapper.addressmapper;

import management.sedef.common.model.entity.mapper.BaseMapper;
import management.sedef.company.model.Address;
import management.sedef.company.model.response.AddressResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressToResponseMapper extends BaseMapper<Address, AddressResponse> {
    static AddressToResponseMapper initialize(){
        return Mappers.getMapper(AddressToResponseMapper.class);
    }
}
