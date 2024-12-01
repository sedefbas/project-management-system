package management.sedef.auth.service.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import management.sedef.auth.exception.RoleNotFoundByNameException;
import management.sedef.auth.exception.UserAlreadyRegisteredException;
import management.sedef.auth.exception.UserNotFoundByEmailException;
import management.sedef.auth.exception.UserPasswordNotValidException;
import management.sedef.auth.model.Role;
import management.sedef.auth.model.Token;
import management.sedef.auth.model.enums.TokenClaims;
import management.sedef.auth.model.request.LoginRequest;
import management.sedef.auth.model.request.RegisterRequest;
import management.sedef.auth.model.request.VerifyRequest;
import management.sedef.auth.port.RoleReadPort;
import management.sedef.auth.service.RegistrationService;
import management.sedef.auth.service.TokenService;
import management.sedef.user.model.User;
import management.sedef.user.model.UserVerification;
import management.sedef.user.model.enums.UserVerificationStatus;
import management.sedef.user.port.UserReadPort;
import management.sedef.user.port.UserSavePort;
import management.sedef.user.port.UserVerificationReadPort;
import management.sedef.user.port.UserVerificationSavePort;
import management.sedef.user.service.UserEmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
class RegistrationServiceImpl implements RegistrationService {

    private final UserReadPort userReadPort;
    private final UserSavePort userSavePort;
    private final RoleReadPort roleReadPort;
    private final PasswordEncoder passwordEncoder;
    private final UserEmailService userEmailService;
    private final UserVerificationReadPort userVerificationReadPort;
    private final UserVerificationSavePort userVerificationSavePort;


    @Override
    @Transactional
    public void register(RegisterRequest request) {

        if (userReadPort.existsByEmail(request.getEmail())) {
            throw new UserAlreadyRegisteredException(request.getEmail());
        }
      //rolname ya member ya compnayowner olacak.
        Role role = roleReadPort.findByName(request.getRoleName())
                .orElseThrow(() -> new RoleNotFoundByNameException(request.getRoleName().name()));

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(role)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        User savedUser = userSavePort.save(user);

        UserVerification userVerification = UserVerification.builder()
                .user(savedUser)
                .status(UserVerificationStatus.WAITING)
                .build();
        userVerificationSavePort.save(userVerification);

        userEmailService.sendVerification(savedUser.getEmail(), userVerification.getId());
    }

    @Override
    public void verify(VerifyRequest verifyRequest) {

    }
}
