package management.sedef.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.service.TokenService;
import management.sedef.common.model.request.TokenRequest;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;
import management.sedef.user.model.mapper.UserEntityToUserMapper;
import management.sedef.user.model.request.UserUpdateRequest;
import management.sedef.user.repository.UserRepository;
import management.sedef.user.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();
    private final TokenService tokenService;

    @Transactional
    public User update(UserUpdateRequest updateRequest) {
        Long userId = tokenService.getUserIdFromToken(updateRequest.getToken());
        Optional<UserEntity> existingUserOpt = userRepository.findById(userId);

        if (existingUserOpt.isPresent()) {
            UserEntity existingUser = existingUserOpt.get();

            // Sadece request'ten gelen alanları güncellemeli ve mevcut verileri korumalısınız
            if (updateRequest.getFirstName() != null) {
                existingUser.setFirstName(updateRequest.getFirstName());
            }
            if (updateRequest.getLastName() != null) {
                existingUser.setLastName(updateRequest.getLastName());
            }
            if (updateRequest.getPhone() != null) {
                existingUser.setPhone(updateRequest.getPhone());
            }
            if (updateRequest.getPhoto() != null) {
                existingUser.setPhoto(updateRequest.getPhoto());
            }

            // Güncellenen kullanıcıyı kaydediyoruz
            userRepository.save(existingUser);

            return userEntityToUserMapper.map(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

}

