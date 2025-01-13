package management.sedef.user.port.adapter;


import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;
import management.sedef.user.model.UserVerification;
import management.sedef.user.model.entity.UserVerificationEntity;
import management.sedef.user.model.enums.UserVerificationType;
import management.sedef.user.model.mapper.UserVerificationEntityToDomainMapper;
import management.sedef.user.model.mapper.UserVerificationToEntityMapper;
import management.sedef.user.port.UserVerificationReadPort;
import management.sedef.user.port.UserVerificationSavePort;
import management.sedef.user.repository.UserVerificationRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserVerificationAdapter  implements UserVerificationReadPort, UserVerificationSavePort {

    private final UserVerificationRepository userVerificationRepository;

    private final UserVerificationEntityToDomainMapper userVerificationEntityToDomainMapper = UserVerificationEntityToDomainMapper.initialize();
    private final UserVerificationToEntityMapper userVerificationToEntityMapper = UserVerificationToEntityMapper.initialize();


    @Override
    public UserVerification save(final UserVerification userVerification) {
        final UserVerificationEntity userVerificationEntity = userVerificationToEntityMapper.map(userVerification);
        final UserVerificationEntity savedUserVerificationEntity = userVerificationRepository.save(userVerificationEntity);
        return userVerificationEntityToDomainMapper.map(savedUserVerificationEntity);
    }

    @Override
    public Optional<UserVerification> findByTypeAndId(UserVerificationType type, Long id) {
        return userVerificationRepository.findByTypeAndId(type,id)
                .map(userVerificationEntityToDomainMapper::map);
    }
}
