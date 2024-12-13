package management.sedef.company.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class CompanyNotFoundException extends AbstractNotFoundException {
    public CompanyNotFoundException(Long id) {
        super("companyId not found with id: " + id);
    }
}
