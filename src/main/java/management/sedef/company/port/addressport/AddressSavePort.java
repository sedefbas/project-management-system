package management.sedef.company.port.addressport;

import management.sedef.company.model.Address;
import management.sedef.company.model.Company;

public interface AddressSavePort {
    Address save(Address address);
}
