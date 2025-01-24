package management.sedef.company.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import management.sedef.company.exception.AddressNotFoundException;
import management.sedef.company.model.Address;

import management.sedef.company.model.mapper.addressmapper.AddressRequestToDomainMapper;
import management.sedef.company.model.request.AddressRequest;
import management.sedef.company.port.addressport.AddressReadPort;
import management.sedef.company.port.addressport.AddressSavePort;
import management.sedef.company.service.AddressService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressReadPort addressReadPort;
    private final AddressSavePort addressSavePort;
    private final AddressRequestToDomainMapper addressRequestToDomainMapper = AddressRequestToDomainMapper.initialize();

    @Override
    public Address findById(Long id) {
        Address address = addressReadPort.findById(id)
                .orElseThrow(()-> new AddressNotFoundException(id));
        return address;
    }

    @Override
    public Address create(AddressRequest request) {
        Address address = addressRequestToDomainMapper.map(request);
        addressSavePort.save(address);
        return address;
    }
}
