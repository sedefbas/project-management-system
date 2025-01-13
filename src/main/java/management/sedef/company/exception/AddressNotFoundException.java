package management.sedef.company.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class AddressNotFoundException extends AbstractNotFoundException {
    public AddressNotFoundException(Long id) {
        super("address id not found: "+id);
    }
}
