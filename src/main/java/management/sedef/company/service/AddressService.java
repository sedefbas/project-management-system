package management.sedef.company.service;

import management.sedef.company.model.Address;
import management.sedef.company.model.request.AddressRequest;

public interface AddressService {
    Address findById(Long id);
    Address create(AddressRequest address);
}
