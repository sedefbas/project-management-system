package management.sedef.company.service.impl;

import lombok.RequiredArgsConstructor;
import management.sedef.company.exception.AddressNotFoundException;
import management.sedef.company.exception.CompanyNotFoundException;
import management.sedef.company.model.Address;

import management.sedef.company.port.addressport.AddressReadPort;
import management.sedef.company.service.AddressService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImp implements AddressService {

    private final AddressReadPort addressReadPort;

    @Override
    public Address findById(Long id) {
        Address address = addressReadPort.findById(id)
                .orElseThrow(()-> new AddressNotFoundException(id));
        return address;
    }
}
