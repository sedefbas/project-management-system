package management.sedef.user.exception;

import management.sedef.common.exception.AbstractNotFoundException;


public class UserNotFoundException extends AbstractNotFoundException{
    public UserNotFoundException(Long id) {
        super("userId not found with id: " + id);
    }

    public UserNotFoundException(String email) {
        super("User with email " + email + " not found");
    }


    public UserNotFoundException( ) {
        super("user not found ");
    }
}
