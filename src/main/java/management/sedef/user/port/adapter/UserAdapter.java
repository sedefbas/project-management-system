package management.sedef.user.port.adapter;

import lombok.RequiredArgsConstructor;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;
import management.sedef.user.model.mapper.UserEntityToUserMapper;
import management.sedef.user.model.mapper.UserToUserEntityMapper;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.port.UserSavePort;
import management.sedef.user.repository.UserRepository;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserAdapter implements UserReadPort, UserSavePort {

    private final UserRepository userRepository;

    private final UserEntityToUserMapper userEntityToUserMapper =UserEntityToUserMapper.initialize();
    private final UserToUserEntityMapper userToUserEntityMapper = UserToUserEntityMapper.initialize();

    @Override
    public Optional<User> findById(final Long id) {
        return userRepository.findById(id).
                map(userEntityToUserMapper::map);
    }

    @Override
    public Optional<User> findByEmail(final String email) {
        return userRepository.findByEmail(email)
                .map(userEntityToUserMapper::map);
    }

    @Override
    public boolean existsByEmail(final String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User save(User user) {
        final UserEntity userEntity = userToUserEntityMapper.map(user);
        final UserEntity savedUserEntity = userRepository.save(userEntity);
        return userEntityToUserMapper.map(savedUserEntity);
    }
}
