package management.sedef.company.port.addressport;

import management.sedef.company.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressReadPort {
    Optional<Address> findById(Long id);
    List<Address> findAll();
}
