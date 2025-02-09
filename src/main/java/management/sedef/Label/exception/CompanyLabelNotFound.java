package management.sedef.Label.exception;

import management.sedef.common.exception.AbstractNotFoundException;

public class CompanyLabelNotFound extends AbstractNotFoundException {
    public CompanyLabelNotFound(String message) {
        super(message);
    }
}
