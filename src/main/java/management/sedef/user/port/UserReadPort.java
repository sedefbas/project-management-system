package management.sedef.user.port;

import management.sedef.user.model.User;

import java.util.Optional;

public interface UserReadPort {

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}