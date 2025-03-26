package management.sedef.user.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.service.TokenService;
import management.sedef.minio.payload.FileResponse;
import management.sedef.minio.service.MinioService;
import management.sedef.user.model.User;
import management.sedef.user.model.entity.UserEntity;
import management.sedef.user.model.mapper.UserEntityToUserMapper;
import management.sedef.user.model.request.UserUpdateRequest;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.repository.UserRepository;
import management.sedef.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserEntityToUserMapper userEntityToUserMapper = UserEntityToUserMapper.initialize();
    private final TokenService tokenService;
    private final UserReadPort readPort;
    private final MinioService minioService; // MinIO servisi eklendi

    private static final String BUCKET_NAME = "user-photos"; // Kullanıcı fotoğraflarının tutulacağı bucket

    @Transactional
    public User update(UserUpdateRequest updateRequest, MultipartFile photoFile) {
        Long userId = tokenService.getUserIdFromToken(updateRequest.getToken());
        Optional<UserEntity> existingUserOpt = userRepository.findById(userId);

        if (existingUserOpt.isPresent()) {
            UserEntity existingUser = existingUserOpt.get();

            // Kullanıcı bilgilerini güncelle
            if (updateRequest.getFirstName() != null) {
                existingUser.setFirstName(updateRequest.getFirstName());
            }
            if (updateRequest.getLastName() != null) {
                existingUser.setLastName(updateRequest.getLastName());
            }
            if (updateRequest.getPhone() != null) {
                existingUser.setPhone(updateRequest.getPhone());
            }

            // Eğer yeni bir fotoğraf yüklenmişse MinIO'ya kaydet
            if (photoFile != null && !photoFile.isEmpty()) {
                FileResponse fileResponse = minioService.putObject(photoFile, BUCKET_NAME, "image");
                existingUser.setPhoto(fileResponse.getFilename()); // URL yerine dosya adı kaydedildi
            } else if (updateRequest.getPhoto() != null) {
                existingUser.setPhoto(updateRequest.getPhoto()); // Direkt olarak gelen dosya ismini kaydet
            }

            userRepository.save(existingUser);
            return userEntityToUserMapper.map(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    public User getUserFromToken(String token) {
        Long userId = tokenService.getUserIdFromToken(token);
        return readPort.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
