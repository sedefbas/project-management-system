package management.sedef.company.exception;

import lombok.NoArgsConstructor;
import management.sedef.common.exception.AbstractNotFoundException;


public class UserNotFoundException extends AbstractNotFoundException{
    public UserNotFoundException(Long id) {
        super("userId not found with id: " + id);
    }

    public UserNotFoundException( ) {
        super("user not found ");
    }
}
